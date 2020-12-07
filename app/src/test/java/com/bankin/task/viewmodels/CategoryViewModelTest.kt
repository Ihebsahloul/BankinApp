package com.bankin.task.viewmodels

import com.bankin.domain.usecase.ResourceRepositoryUseCase
import com.bankin.task.BaseViewModelTest
import com.bankin.task.categories.CategoryRepositoryViewModel
import com.bankin.task.mappers.toPresentation
import com.bankin.task.utils.SampleData
import com.bankin.task.utils.observeOnce
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CategoryViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var categoryUseCase: ResourceRepositoryUseCase
    private lateinit var categoryViewModel: CategoryRepositoryViewModel

    @Before
    fun setup() {
        categoryViewModel = CategoryRepositoryViewModel(categoryUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldReceiveSearchResults() {
        runBlockingTest {
            setMockAnswer()
            categoryViewModel.executeCategoryRepositorySearch()
            categoryViewModel.searchResultsCategories.observeOnce {
                Truth.assertThat(it)
                    .isEqualTo(SampleData.categoriesSearchResults.map { repo -> repo.toPresentation() })
            }
        }
    }

    private suspend fun setMockAnswer() {
        given(categoryUseCase(false)).willReturn(flow {
            emit(SampleData.categoriesSearchResults)
        })
    }
}