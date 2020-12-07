package com.bankin.task.categories

import android.R.attr.data
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bankin.task.R
import com.bankin.task.Utilities.hide
import com.bankin.task.Utilities.initRecyclerViewWithLineDecoration
import com.bankin.task.Utilities.show
import com.bankin.task.Utilities.showSnackbar
import com.bankin.task.commons.Error
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.models.ResourceUiModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_category_repository.*
import kotlinx.android.synthetic.main.fragment_sub_categories.*
import kotlinx.android.synthetic.main.layout_error_status_notifier.*
import kotlinx.android.synthetic.main.layout_status_loading.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import kotlin.properties.Delegates


class SubCategoriesFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val subCategoryViewModel: SubCategoryViewModel by viewModels { viewModelFactory }

    private lateinit var viewModel: CategoryRepositoryViewModel

    private lateinit var subCategoriesList: java.util.ArrayList<ResourceUiModel>

    private var parentId by Delegates.notNull<Int>()


    @InternalCoroutinesApi
    private val SubCategoriesAdapter: SubCategoriesAdapter by lazy {
        SubCategoriesAdapter {

        }
    }

    /*@InternalCoroutinesApi
    private fun observeUiState() {
        subCategoryViewModel.uiState.observe(this, Observer {
            when (it) {
                is Success -> fetchSubCategories()
            }
        })
    }*/


    @InternalCoroutinesApi
    private fun fetchSubCategories() {

        //   viewModel.executeSubCategoryRepositorySearch(parentId ,forceRefresh)
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this, Observer {
            when (it) {
                is Loading -> displayLoadingState()
                is Success -> hideLoadingState()
                is Error -> null
            }
        })
    }

    @InternalCoroutinesApi
    private fun observeSubCategoriesResults() {

        viewModel.searchSubCategories.observe(this, Observer {
            displaySubCategories(viewModel.searchSubCategories.value!!)
        })

    }

    @InternalCoroutinesApi
    private fun displaySubCategories(repoSearchResult: List<ResourceUiModel>) {
        if (repoSearchResult.isNotEmpty()) {
            hideLoadingState()
           // subCategoriesList = repoSearchResult as java.util.ArrayList<ResourceUiModel>


            rvSubCatregory.apply {
                adapter =
                        SubCategoriesAdapter.apply {
                            submitList(
                                    repoSearchResult
                            )
                        }
                initRecyclerViewWithLineDecoration(this@SubCategoriesFragment.requireContext())
            }

            subCategoriesList = repoSearchResult as java.util.ArrayList<ResourceUiModel>
        } else {
            displayLoadingState()
        }

    }

    private fun displayNoSearchResults() {
        showSnackbar(
                rvSubCatregory,
                getString(R.string.label_no_times)
        )
    }

    private fun displayLoadingState() {
        rvSubCatregory.hide()
        containerShimmer.show()
        containerShimmer.startShimmer()
        containerShimmer.showShimmer(true)
    }

    private fun hideLoadingState() {
        rvSubCatregory.show()
        containerShimmer.hide()
        containerShimmer.stopShimmer()
    }

    @InternalCoroutinesApi
    private fun addListeners() {
        /*swipeRepoRefresh.setOnRefreshListener {
            fetchCategories(true)
            swipeRepoRefresh.isRefreshing = false
        }


        vInvisible.setOnClickListener {
            s//howAlertMessage(getString(R.string.error_offline_test_scenario))
        }*/
    }

    companion object {

        const val TAG = "SubCategoriesFragment"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: Int, subTitle: String?): SubCategoriesFragment {
            val args = Bundle()
            args.putInt(KEY_TITLE, title)
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

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        AndroidInjection.inject(activity!!)
        viewModel = ViewModelProviders.of(activity!!).get(CategoryRepositoryViewModel::class.java)
        parentId = arguments?.getInt(KEY_TITLE)!!
        sub_categories_title.text = arguments?.getString(KEY_SUBTITLE)
        observeUiState()
        observeSubCategoriesResults()

        super.onViewCreated(view, savedInstanceState)

    }

    fun clear(data : ArrayList<ResourceUiModel>) {
        val size: Int = data.size
        if (size > 0) {
            for (i in 0 until size) {
                data.removeAt(0)
            }
           // notifyItemRangeRemoved(0, size)
        }
    }

    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )

    }

    override fun onPause() {
        super.onPause()
        //subCategoriesList.clear()
      //  clear(subCategoriesList as ArrayList<ResourceUiModel>)

    }

    override fun onStop() {
        super.onStop()
        subCategoriesList.clear()
        //  clear(subCategoriesList as ArrayList<ResourceUiModel>)

    }
}