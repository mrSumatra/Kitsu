package lsvapp.kitsu.presentation.utils.widget.reaction.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.ItemReactionBinding
import lsvapp.kitsu.presentation.utils.toHumanDataTime

class ReactionAdapterViewHolder(
    private val binding: ItemReactionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReactionAdapterItem) {
        binding.authorAvatar.load(item.reaction.author.avatar?.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = item.reaction.author.name
            ?: binding.root.context.getString(R.string.command_profile_no_name)
        binding.authorAvatar.setOnClickListener {
            item.action.invoke()
        }
        binding.create.text = item.reaction.createdAt.toHumanDataTime()
        binding.content.text = item.reaction.reaction
    }
}