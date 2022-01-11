package lsvapp.kitsu.presentation.utils.widget.adapter

import android.util.TypedValue
import android.view.View
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
    ) : ContentViewerViewHolder<ContentViewerItem.Anime>(binding.root) {

        override fun bind(item: ContentViewerItem.Anime) {
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

    class MangaViewHolder(
        private val binding: MovieItemBinding
    ) : ContentViewerViewHolder<ContentViewerItem.Manga>(binding.root) {

        override fun bind(item: ContentViewerItem.Manga) {
            TODO("Not yet implemented")
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