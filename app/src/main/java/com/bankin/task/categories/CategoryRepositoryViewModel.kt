package com.bankin.task.categories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bankin.domain.countries.model.Resource
import com.bankin.domain.usecase.ResourceRepositoryUseCase
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.commons.UiStateViewModel
import com.bankin.task.mappers.toPresentation
import com.bankin.task.models.ResourceUiModel
import com.bankin.task.models.SortType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CategoryRepositoryViewModel @Inject constructor(
    private val CategoryRepositoryUseCase: ResourceRepositoryUseCase
) : UiStateViewModel() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null

    val name = MutableLiveData<Int>()

    val searchResultsCategories: LiveData<List<ResourceUiModel>>
        get() = _searchResultsCategoryRepository

    private var _searchResultsCategoryRepository: MutableLiveData<List<ResourceUiModel>> =
        MutableLiveData()

    val searchSubCategories: LiveData<List<ResourceUiModel>>
        get() = _searchSubCategoryRepository

    private var _searchSubCategoryRepository: MutableLiveData<List<ResourceUiModel>> =
            MutableLiveData()


    fun executeCategoryRepositorySearch(forceRefresh: Boolean = false) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            CategoryRepositoryUseCase(forceRefresh).collect { results ->
            var categoriesList : ArrayList<Resource> = ArrayList()
            for (i in 0..results.size-1)
            {
                if (results.get(i).parent==null)
                {
                  categoriesList.add( results.get(i))
                }
            }
                _searchResultsCategoryRepository.value = categoriesList.map { it.toPresentation() } }
            _uiState.value = Success
        }
    }

    fun executeSubCategoryRepositorySearch(parentId : Int?  ,forceRefresh: Boolean = false) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            CategoryRepositoryUseCase(false).collect { results ->
                var subCategoriesList : ArrayList<Resource> = ArrayList()
                for (i in 0..results.size-1)
                {
                    if (parentId == results.get(i).parent?.id)
                    {
                        subCategoriesList.add( results.get(i))
                    }
                }
                _searchSubCategoryRepository.value = subCategoriesList.map { it.toPresentation() } }
            _uiState.value = Success
        }
    }

    fun sortTrendingRepoResult(sortType: SortType) {
        if (_uiState.value is Success) {
            val list = _searchResultsCategoryRepository.value
            when (sortType) {
                is SortType.SortByName -> {
                    sortByNames(list)
                }
                is SortType.SortByValue -> {
                    sortByValue(list)
                }
            }
        }
    }

    private fun sortByNames(trendingRepo: List<ResourceUiModel>?) {
        _uiState.value = Loading
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            _searchResultsCategoryRepository.value = trendingRepo?.sortedBy { it.name }
            delay(1000)
            _uiState.value = Success
        }
    }

    private fun sortByValue(trendingRepo: List<ResourceUiModel>?) {
        _uiState.value = Loading
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            _searchResultsCategoryRepository.value = trendingRepo?.sortedByDescending { it.custom }
            delay(1000)
            _uiState.value = Success
        }
    }

    fun sendName(text: String) {
        name.value = text
    }

    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
        searchJob?.cancel()
    }
}