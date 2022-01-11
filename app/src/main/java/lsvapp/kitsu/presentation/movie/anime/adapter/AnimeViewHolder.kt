package lsvapp.kitsu.presentation.movie.anime.adapter

import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import lsvapp.kitsu.databinding.AnimeItemBinding

class AnimeViewHolder(
    private val binding: AnimeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AnimeAdapterItem) {
        binding.image.load(item.anime.posterImage.medium) {
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
        binding.title.text = item.anime.canonicalTitle
        binding.rating.text = item.anime.averageRating
        binding.ageRating.text = item.anime.ageRating.toString()

        binding.root.setOnClickListener {
            item.action.invoke()
        }
    }
}