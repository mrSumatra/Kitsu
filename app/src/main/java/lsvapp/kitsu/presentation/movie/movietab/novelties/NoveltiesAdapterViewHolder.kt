package lsvapp.kitsu.presentation.movie.movietab.novelties

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import lsvapp.kitsu.databinding.ItemNoveltiesBinding

class NoveltiesAdapterViewHolder(
    private val binding: ItemNoveltiesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NoveltiesAdapterItem) {
        binding.cover.load(item.anime.coverImage?.original ?: item.anime.posterImage.original) {
            transformations(BlurTransformation(binding.root.context))
        }
        binding.poster.load(item.anime.posterImage.original)
        binding.title.text = item.anime.canonicalTitle
        item.anime.ageRating?.let {
            binding.ageRating.apply {
                isVisible = true
                text = it.toString()
            }
        }
        binding.root.setOnClickListener {
            item.action.invoke()
        }
    }
}