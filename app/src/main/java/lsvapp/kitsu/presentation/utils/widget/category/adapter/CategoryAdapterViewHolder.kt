package lsvapp.kitsu.presentation.utils.widget.category.adapter

import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.databinding.ItemCategoryBinding

class CategoryAdapterViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.categoryTitle.text = item
    }

}