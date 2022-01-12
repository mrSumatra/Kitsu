package lsvapp.kitsu.presentation.group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import lsvapp.kitsu.databinding.ItemGroupeBinding

class GroupeTabAdapter : PagingDataAdapter<GroupeTabAdapterItem, GroupeTabAdapterViewHolder>(differ) {
    override fun onBindViewHolder(holder: GroupeTabAdapterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else error("Item is null. getItemViewType")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupeTabAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupeBinding.inflate(inflater, parent, false)
        return GroupeTabAdapterViewHolder(binding)
    }

    companion object {
        private val differ = object : DiffUtil.ItemCallback<GroupeTabAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: GroupeTabAdapterItem,
                newItem: GroupeTabAdapterItem
            ) = oldItem == newItem || oldItem.groupe == newItem.groupe

            override fun areContentsTheSame(
                oldItem: GroupeTabAdapterItem,
                newItem: GroupeTabAdapterItem
            ) = oldItem == newItem
        }
    }
}