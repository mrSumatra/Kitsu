package lsvapp.kitsu.presentation.movie.movietab.adapter

import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.CategoryItemBinding

class CategoriesAdapterViewHolder(
    private val binding: CategoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategoriesAdapterItem, position: Int) {
        binding.card.background.setTint(
            binding.root.context.getColor(colorList[position])
        )
        binding.title.text = item.category.title
        binding.card.setOnClickListener {
            item.action.invoke()
        }
    }

    companion object {
        val colorList = listOf(
            R.color.blue_a200,
            R.color.red_a200,
            R.color.yellow_a200,
            R.color.green_a200,
            R.color.orange_a200,
            R.color.light_blue_a200,
            R.color.purple_a200,
            R.color.red_a200,
            R.color.yellow_a200,
            R.color.blue_a200,
        )
    }
}