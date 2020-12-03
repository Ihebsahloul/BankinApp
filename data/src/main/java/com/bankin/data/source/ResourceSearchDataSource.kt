package com.bankin.data.source


import com.bankin.data.db.ResourceRepoDao
import com.bankin.data.mappers.toEntity
import com.bankin.data.model.ResourceEntity
import com.bankin.data.model.ResourceRepoApiService
import com.bankin.data.utils.AppLogger
import com.bankin.data.utils.SharedPrefsHelper
import com.bankin.data.utils.isTimeWithInInterval
import com.bankin.domain.countries.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResourceSearchDataSource @Inject constructor(
    private val apiService: ResourceRepoApiService,
    private val resourceRepoDao: ResourceRepoDao,
    private val sharedPrefsHelper: SharedPrefsHelper
) {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    suspend fun query(forceRefresh: Boolean): Flow<List<ResourceEntity>> {
        val syncUpIntervalInSeconds = 2L * 3_600
        val isCacheAvailable = resourceRepoDao.isCategoriesCacheAvailable() > 0
        val lastSyncUpTime =
            sharedPrefsHelper[SharedPrefsHelper.PREF_KEY_REPO_LAST_UPDATED_TIME, 0L]
        val isTimeSurpassed =
            isTimeWithInInterval(
                syncUpIntervalInSeconds,
                System.currentTimeMillis(),
                lastSyncUpTime
            )
        if (forceRefresh) {
            AppLogger.logD(
                ResourceSearchDataSource::class.java.name,
                "{Clearing cache - action Force Refresh}"
            )
            resourceRepoDao.deleteAllCategories()
        } else if (isCacheAvailable && !isTimeSurpassed) {
            AppLogger.logD(
                ResourceSearchDataSource::class.java.name, "{Cache Found}"
            )
            return flow { emit(resourceRepoDao.allCategories()) }
        }
        val searchResponse = apiService.resourceRepoSearchRepositories()
        val searchDataModels = mutableListOf<ResourceEntity>()
        searchResponse.resources.forEachIndexed { index, searchResult ->
            searchDataModels.add(searchResult.toEntity(index))
        }
        //save to data base
        resourceRepoDao.saveCategories(searchDataModels)
        //update time after a successful sync up with backend
        sharedPrefsHelper.put(
            SharedPrefsHelper.PREF_KEY_REPO_LAST_UPDATED_TIME,
            System.currentTimeMillis()
        )
        return flow { emit(searchDataModels) }
    }
}