package lsvapp.kitsu.presentation.utils.widget.content.adapter

import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import lsvapp.kitsu.databinding.AllMovieItemBinding
import lsvapp.kitsu.databinding.MovieItemBinding

sealed class ContentViewerViewHolder<T : ContentViewerItem>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

    class AnimeViewHolder(
        private val binding: MovieItemBinding
    ) : ContentViewerViewHolder<ContentViewerItem.Content>(binding.root) {

        override fun bind(item: ContentViewerItem.Content) {
            binding.image.load(item.imageLink) {
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
            binding.title.text = item.title
            item.rating?.let {
                binding.rating.apply {
                    isVisible = true
                    text = it
                }
            }
            item.ageRating?.let {
                binding.ageRating.apply {
                    isVisible = true
                    text = it
                }
            }
            item.desc?.let {
                binding.desc.apply {
                    isVisible = true
                    text = it
                }
            }
            binding.root.setOnClickListener {
                item.action.invoke()
            }
        }
    }

    class AllMovieViewHolder(
        private val binding: AllMovieItemBinding
    ) : ContentViewerViewHolder<ContentViewerItem.AllMovie>(binding.root) {

        override fun bind(item: ContentViewerItem.AllMovie) {
            binding.title.text = item.title

            binding.root.setOnClickListener {
                item.action.invoke()
            }
        }
    }
}