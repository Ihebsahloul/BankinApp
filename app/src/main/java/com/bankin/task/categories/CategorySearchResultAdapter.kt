package com.bankin.task.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bankin.task.models.ResourceRepositoryUiModel
import com.mvvmclean.trendingrepos.models.ResourceRepositoryUiModel

class CategorySearchResultAdapter(val onClick: (ResourceRepositoryUiModel) -> Unit) :
    ListAdapter<ResourceRepositoryUiModel, CategorySearchResultAdapter.TrendingRepoViewHolder>(
        TrendingRepoDiffUtil
    ) {

    private var lastSelectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRepoViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return TrendingRepoViewHolder(ItemCategoryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TrendingRepoViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it, holder.layoutPosition) }

    inner class TrendingRepoViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelRepository: ResourceRepositoryUiModel, position: Int) {
            binding.searchedTrendingRepo = modelRepository
            binding.executePendingBindings()
            populateData(modelRepository)
            binding.containerTrendingRepo.setOnClickListener {
                expandCollapse(modelRepository, position)
            }
            binding.containerTrendingRepo.setOnLongClickListener {
                onClick(modelRepository)
                return@setOnLongClickListener true
            }
        }

        private fun populateData(modelRepository: ResourceRepositoryUiModel) {
            binding.apply {
                modelRepository.let { it ->
                    it.languageColor?.let { binding.tvRepoLang.setDrawableBackgroundColor(it) }
                    it.avatar.let { binding.ivUserAvatar.circleCrop(it) }
                    if (it.expand) {
                        groupExpansion.show()
                    } else {
                        groupExpansion.hide()
                    }
                }
            }
        }

        private fun expandCollapse(
            modelRepository: ResourceRepositoryUiModel,
            position: Int
        ) {
            //Expand on clicking same item in collapsed state
            if (lastSelectedPosition == position && !modelRepository.expand) {
                modelRepository.expand = true
                notifyItemChanged(position)
                return
            }
            //Collapse previous item
            if (lastSelectedPosition != RecyclerView.NO_POSITION) {
                getItem(lastSelectedPosition).expand = false
                notifyItemChanged(lastSelectedPosition)
            }
            //Return when click on already expanded item
            if (lastSelectedPosition == position) {
                return
            }
            //Expand clicked  item
            modelRepository.let {
                it.expand = !it.expand
            }
            notifyItemChanged(position)
            lastSelectedPosition = position
        }
    }

    companion object {
        val TrendingRepoDiffUtil =
            object : DiffUtil.ItemCallback<ResourceRepositoryUiModel>() {
                override fun areItemsTheSame(
                    oldItem: ResourceRepositoryUiModel,
                    newItem: ResourceRepositoryUiModel
                ): Boolean = oldItem.resource_uri == newItem.resource_uri

                override fun areContentsTheSame(
                    oldItem: ResourceRepositoryUiModel,
                    newItem: ResourceRepositoryUiModel
                ): Boolean = oldItem == newItem

            }
    }
}