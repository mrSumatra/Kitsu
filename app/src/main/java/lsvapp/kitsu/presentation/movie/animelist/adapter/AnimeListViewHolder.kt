package lsvapp.kitsu.presentation.movie.animelist.adapter

import android.util.TypedValue
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import lsvapp.kitsu.databinding.AnimeItemBinding

class AnimeListViewHolder(
    private val binding: AnimeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemList: AnimeListAdapterItem) {
        binding.image.load(itemList.anime.posterImage.medium) {
            transformations(
                RoundedCornersTransformation(
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        4f,
                        binding.root.context.resources.displayMetrics
                    )
                )
            )
        }
        itemList.anime.ageRating?.let {
            binding.ageRating.isVisible = true
            binding.ageRating.text = it.toString()
        }
        itemList.anime.averageRating?.let {
            binding.rating.isVisible = true
            binding.rating.text = it
        }
        binding.title.text = itemList.anime.canonicalTitle

        binding.root.setOnClickListener {
            itemList.action.invoke()
        }
    }
}