package com.bankin.task.categories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bankin.domain.usecase.ResourceRepositoryUseCase
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.commons.UiStateViewModel
import com.bankin.task.models.ResourceCategoryUiModel
import com.bankin.task.models.SortType
import io.reactivex.internal.operators.flowable.FlowableCollect
import kotlinx.coroutines.*
import org.intellij.lang.annotations.Flow
import javax.annotation.Resource
import javax.inject.Inject

class CategoryRepositoryViewModel @Inject constructor(
    private val CategoryRepositoryUseCase: ResourceRepositoryUseCase
) : UiStateViewModel() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null

    val searchResultsCategories: LiveData<List<ResourceCategoryUiModel>>
        get() = _searchResultsCategoryRepository

    private var _searchResultsCategoryRepository: MutableLiveData<List<ResourceCategoryUiModel>> =
        MutableLiveData()

    @InternalCoroutinesApi
    fun executeCategoryRepositorySearch(forceRefresh: Boolean = false) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            CategoryRepositoryUseCase(forceRefresh).collect { results ->
                _searchResultsCategoryRepository.value = results.map { it.toPresentation() }
            }
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

    private fun sortByNames(trendingRepo: List<ResourceCategoryUiModel>?) {
        _uiState.value = Loading
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            _searchResultsCategoryRepository.value = trendingRepo?.sortedBy { it.name }
            delay(1000)
            _uiState.value = Success
        }
    }

    private fun sortByValue(trendingRepo: List<ResourceCategoryUiModel>?) {
        _uiState.value = Loading
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            _searchResultsCategoryRepository.value = trendingRepo?.sortedByDescending { it.custom }
            delay(1000)
            _uiState.value = Success
        }
    }

    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
        searchJob?.cancel()
    }
}