package com.bankin.task.categories

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bankin.task.Utilities.hide
import com.bankin.task.Utilities.show
import com.bankin.task.databinding.ItemResourceBinding
import com.bankin.task.models.ResourceUiModel
import java.util.*

class CategorySearchResultAdapter(val onClick: (ResourceUiModel) -> Unit) :
    ListAdapter<ResourceUiModel, CategorySearchResultAdapter.ResourceViewHolder>(
        ResourceDiffUtil
    ) {

    private var lastSelectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return ResourceViewHolder(ItemResourceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it, holder.layoutPosition) }

    inner class ResourceViewHolder(private val binding: ItemResourceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelRepository: ResourceUiModel, position: Int) {
            binding.searchedResource = modelRepository
            binding.executePendingBindings()
            populateData(modelRepository)
            binding.containerResource.setOnClickListener {
                expandCollapse(modelRepository, position)
            }
            binding.containerResource.setOnLongClickListener {
                onClick(modelRepository)
                return@setOnLongClickListener true
            }
        }

        private fun populateData(modelRepository: ResourceUiModel) {
            binding.apply {
                modelRepository.let { it ->
                    it.name?.let { /*binding.tvRepoLang.setDrawableBackgroundColor(it) */}
                    it.name?.let {
                        val rnd = Random()
                        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                        binding.ivCategoryIcon.setColorFilter(color)
}
                    if (it.expand) {
                        groupExpansion.show()
                    } else {
                        groupExpansion.hide()
                    }
                }
            }
        }

        private fun expandCollapse(
            modelRepository: ResourceUiModel,
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
        val ResourceDiffUtil =
            object : DiffUtil.ItemCallback<ResourceUiModel>() {
                override fun areItemsTheSame(
                    oldItem: ResourceUiModel,
                    newItem: ResourceUiModel
                ): Boolean = oldItem.resource_uri == newItem.resource_uri

                override fun areContentsTheSame(
                    oldItem: ResourceUiModel,
                    newItem: ResourceUiModel
                ): Boolean = oldItem == newItem

            }
    }
}