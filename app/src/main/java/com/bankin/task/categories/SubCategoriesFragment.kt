package com.bankin.task.categories

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bankin.data.utils.AppLogger
import com.bankin.task.R
import com.bankin.task.Utilities.*
import com.bankin.task.commons.Error
import com.bankin.task.commons.Loading
import com.bankin.task.commons.Success
import com.bankin.task.models.ResourceUiModel
import com.bankin.task.models.SortType
import dagger.android.AndroidInjection
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_category_repository.*
import kotlinx.android.synthetic.main.fragment_sub_categories.*
import kotlinx.android.synthetic.main.layout_status_loading.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import kotlin.properties.Delegates

class SubCategoriesFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val subCategoryViewModel: SubCategoryViewModel by viewModels { viewModelFactory }

    private lateinit var viewModel: CategoryRepositoryViewModel

    private var  parentId by Delegates.notNull<Int>()


    @InternalCoroutinesApi
    private val SubCategoriesAdapter: SubCategoriesAdapter by lazy {
        SubCategoriesAdapter {

       }
    }

    @InternalCoroutinesApi
    private fun observeUiState() {
        subCategoryViewModel.uiState.observe(this, Observer {
            when (it) {
                is Success -> fetchSubCategories(parentId,false)
            }
        })
    }


    @InternalCoroutinesApi
    private fun fetchSubCategories(parentId : Int?, forceRefresh: Boolean = false) {

      //   viewModel.executeSubCategoryRepositorySearch(parentId ,forceRefresh)
    }

    @InternalCoroutinesApi
    private fun observeSubCategoriesResults() {

        viewModel.searchSubCategories.observe(this, Observer {
            displaySubCategories(viewModel?.searchSubCategories.value!!)
        })

    }

    @InternalCoroutinesApi
    private fun displaySubCategories(repoSearchResult: List<ResourceUiModel>) {
        if (repoSearchResult.isNotEmpty()) {

            rvSubCatregory.apply {
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
                rvSubCatregory,
                getString(R.string.label_no_times)
        )
    }

    companion object {

        const val TAG = "SubCategoriesFragment"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: Int, subTitle: String): SubCategoriesFragment {
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
        AndroidInjection.inject(activity!!)
        viewModel = ViewModelProviders.of(activity!!).get(CategoryRepositoryViewModel::class.java)
       parentId = arguments?.getInt(KEY_TITLE)!!
        observeSubCategoriesResults()
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