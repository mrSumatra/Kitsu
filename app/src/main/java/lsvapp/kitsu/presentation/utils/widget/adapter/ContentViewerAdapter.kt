package lsvapp.kitsu.presentation.utils.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.databinding.AllMovieItemBinding
import lsvapp.kitsu.databinding.MovieItemBinding

class ContentViewerAdapter : RecyclerView.Adapter<ContentViewerViewHolder<*>>() {

    var items: List<ContentViewerItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewerViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CONTENT_TYPE -> {
                val binding = MovieItemBinding.inflate(inflater, parent, false)
                ContentViewerViewHolder.AnimeViewHolder(binding)
            }
            ALL_MOVIE_TYPE -> {
                val binding = AllMovieItemBinding.inflate(inflater, parent, false)
                ContentViewerViewHolder.AllMovieViewHolder(binding)
            }
            else -> error("Unknown position")
        }
    }

    override fun onBindViewHolder(holder: ContentViewerViewHolder<*>, position: Int) =
        when (holder) {
            is ContentViewerViewHolder.AnimeViewHolder -> holder.bind(items[position] as ContentViewerItem.Content)
            is ContentViewerViewHolder.AllMovieViewHolder -> holder.bind(items[position] as ContentViewerItem.AllMovie)
        }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ContentViewerItem.Content -> CONTENT_TYPE
        is ContentViewerItem.AllMovie -> ALL_MOVIE_TYPE
    }

    private companion object {
        private const val CONTENT_TYPE = 0
        private const val ALL_MOVIE_TYPE = 1
    }
}