package lsvapp.kitsu.presentation.movie.animelist.adapter

import android.util.TypedValue
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
        binding.title.text = itemList.anime.canonicalTitle
        binding.rating.text = itemList.anime.averageRating
        binding.ageRating.text = itemList.anime.ageRating.toString()

        binding.root.setOnClickListener {
            itemList.action.invoke()
        }
    }
}