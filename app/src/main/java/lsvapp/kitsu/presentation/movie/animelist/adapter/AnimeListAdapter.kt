package lsvapp.kitsu.presentation.movie.animelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.AnimeItemBinding

class AnimeListAdapter : PagingDataAdapter<AnimeListAdapterItem, AnimeListViewHolder>(differ) {

    override fun onBindViewHolder(holderList: AnimeListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holderList.bind(item)
        } else error("Item is null. getItemViewType")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnimeItemBinding.inflate(inflater, parent, false)
        return AnimeListViewHolder(binding)
    }

    override fun getItemViewType(position: Int) = R.layout.movie_item

    companion object {
        private val differ = object : DiffUtil.ItemCallback<AnimeListAdapterItem>() {
            override fun areItemsTheSame(
                oldItemList: AnimeListAdapterItem,
                newItemList: AnimeListAdapterItem
            ) = oldItemList.anime == newItemList.anime || oldItemList == newItemList

            override fun areContentsTheSame(
                oldItemList: AnimeListAdapterItem,
                newItemList: AnimeListAdapterItem
            ) = oldItemList == newItemList
        }
    }
}