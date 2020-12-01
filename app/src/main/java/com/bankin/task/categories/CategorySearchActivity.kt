package com.bankin.task.categories

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bankin.task.R
import com.bankin.task.Utilities.*
import com.bankin.task.base.BaseActivity
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.models.ResourceCategoryUiModel
import com.bankin.task.models.SortType
import kotlinx.android.synthetic.main.activity_category_repository.*
import kotlinx.android.synthetic.main.layout_error_status_notifier.*
import kotlinx.android.synthetic.main.layout_status_loading.*
import javax.inject.Inject

class CategorySearchActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val CategoryRepositoryViewModel: CategoryRepositoryViewModel by viewModels { viewModelFactory }

    private var mBackPressed: Long = 0

    private val CategoryRepoSearchResultAdapter: CategorySearchResultAdapter by lazy {
        CategorySearchResultAdapter {
            launchUrl(this, it.resource_uri)
        }
    }

    override fun initComponents(savedInstanceState: Bundle?) {
        enableHomeUp(R.string.title_trending)
        observeUiState()
        observeSearchResults()
        addListeners()
        fetchTrendingRepositories()
    }

    override fun getLayoutId() = R.layout.activity_category_repository

    private fun observeUiState() {
        CategoryRepositoryViewModel.uiState.observe(this, Observer {
            when (it) {
                is Loading -> displayLoadingState()
                is Success -> hideLoadingState()
                is Error -> displayErrorState(it.error)
            }
        })
    }

    private fun observeSearchResults() {
        CategoryRepositoryViewModel.searchResultsCategories.observe(this, Observer {
            displaySearchResults(it)
        })
    }

    private fun addListeners() {
        swipeRepoRefresh.setOnRefreshListener {
            fetchTrendingRepositories(true)
            swipeRepoRefresh.isRefreshing = false
        }
        lookUpButton.setOnClickListener {
            fetchTrendingRepositories()
        }

        vInvisible.setOnClickListener {
            showAlertMessage(getString(R.string.error_offline_test_scenario))
        }
    }

    private fun fetchTrendingRepositories(forceRefresh: Boolean = false) {
        CategoryRepositoryViewModel.executeCategoryRepositorySearch(forceRefresh)
    }

    private fun displayLoadingState() {
        rvRepository.hide()
        containerShimmer.show()
        containerShimmer.showShimmer(true)
    }

    private fun hideLoadingState() {
        rvRepository.show()
        containerShimmer.hide()
        containerShimmer.stopShimmer()
    }

    private fun displaySearchResults(repoSearchResult: List<ResourceCategoryUiModel>) {
        if (repoSearchResult.isNotEmpty()) {
            if (layoutError.isVisible) {
                layoutError.hide()
            }

            rvRepository.apply {
                adapter =
                    ScaleInAnimationAdapter(CategoryRepoSearchResultAdapter.apply {
                        submitList(
                            repoSearchResult
                        )
                    })
                initRecyclerViewWithLineDecoration(this@CategorySearchActivity)
            }
        } else displayNoSearchResults()
    }

    private fun displayNoSearchResults() {
        showSnackbar(
            rvRepository,
            getString(R.string.label_no_times)
        )
    }

    private fun displayErrorState(error: Throwable) {
        hideLoadingState()
        layoutError.show()
        showSnackbar(rvRepository, "${error.message}")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_stars -> {
                CategoryRepositoryViewModel.sortTrendingRepoResult(SortType.SortByValue)
                showSnackbar(rvRepository, getString(R.string.label_sorted_by_star))
                true
            }
            R.id.action_sort_by_name -> {
                CategoryRepositoryViewModel.sortTrendingRepoResult(SortType.SortByName)
                showSnackbar(rvRepository, getString(R.string.label_sorted_repo_name))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (mBackPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            toast(getString(R.string.info_again_exit))
        }
        mBackPressed = System.currentTimeMillis()
    }

}
