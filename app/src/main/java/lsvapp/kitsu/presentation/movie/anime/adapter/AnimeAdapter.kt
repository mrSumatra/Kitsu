package lsvapp.kitsu.presentation.movie.anime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.AnimeItemBinding

class AnimeAdapter : PagingDataAdapter<AnimeAdapterItem, AnimeViewHolder>(differ) {

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else error("Item is null. getItemViewType")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnimeItemBinding.inflate(inflater, parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemViewType(position: Int) = R.layout.movie_item

    companion object {
        private val differ = object : DiffUtil.ItemCallback<AnimeAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: AnimeAdapterItem,
                newItem: AnimeAdapterItem
            ) = oldItem.anime == newItem.anime || oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AnimeAdapterItem,
                newItem: AnimeAdapterItem
            ) = oldItem == newItem
        }
    }
}