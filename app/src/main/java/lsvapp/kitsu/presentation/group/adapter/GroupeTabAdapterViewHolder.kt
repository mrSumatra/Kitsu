package lsvapp.kitsu.presentation.group.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import lsvapp.kitsu.databinding.ItemGroupeBinding

class GroupeTabAdapterViewHolder(
    private val binding: ItemGroupeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GroupeTabAdapterItem) {
        binding.image.load(item.groupe.avatar?.original)
        binding.title.text = item.groupe.name
        binding.userCount.text = item.groupe.membersCount
        binding.about.text = item.groupe.about
    }
}