package com.bankin.task.categories

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bankin.data.utils.AppLogger
import com.bankin.task.R
import com.bankin.task.Utilities.*
import com.bankin.task.commons.Error
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.models.ResourceUiModel
import com.bankin.task.models.SortType
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_category_repository.*
import kotlinx.android.synthetic.main.layout_status_loading.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class SubCategoriesFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val subCategoryViewModel: SubCategoryViewModel by viewModels { viewModelFactory }

    private lateinit var viewModel: CategoryRepositoryViewModel


    private val SubCategoriesAdapter: SubCategoriesAdapter by lazy {
        SubCategoriesAdapter {
            //launchUrl(activity!!, it.resource_uri)
        }
    }

    /*@InternalCoroutinesApi
    override fun initComponents(savedInstanceState: Bundle?) {

        observeUiState()
        observeSearchResults()
        addListeners()
        fetchCategories()
    }

    override fun getLayoutId() = R.layout.fragment_sub_categories*/

    @InternalCoroutinesApi
    private fun observeUiState() {
        subCategoryViewModel.uiState.observe(this, Observer {
            when (it) {
                is Success -> fetchSubCategories(false)
            }
        })
    }


    @InternalCoroutinesApi
    private fun fetchSubCategories( forceRefresh: Boolean = false) {

         viewModel.executeSubCategoryRepositorySearch( forceRefresh)
    }

    @InternalCoroutinesApi
    private fun observeSubCategoriesResults() {
        viewModel.searchSubCategories.observe(this, Observer {
            displaySubCategories(it)
        })
    }

    private fun displaySubCategories(repoSearchResult: List<ResourceUiModel>) {
        if (repoSearchResult.isNotEmpty()) {
            if (layoutError.isVisible) {
                layoutError.hide()
            }

            rvRepository.apply {
                adapter =
                        ScaleInAnimationAdapter(SubCategoriesAdapter.apply {
                            submitList(
                                    repoSearchResult
                            )
                        })
                initRecyclerViewWithLineDecoration(this@SubCategoriesFragment.requireContext())
            }
        } else displayNoSearchResults()
    }

    private fun displayNoSearchResults() {
        showSnackbar(
                rvRepository,
                getString(R.string.label_no_times)
        )
    }

    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): SubCategoriesFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = SubCategoriesFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sub_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}