package lsvapp.kitsu.presentation.feed.postdetails.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.CommentItemBinding

class CommentViewHolder(
    private val binding: CommentItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CommentItem) {
        binding.authorAvatar.load(item.comment.author.avatar.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = item.comment.author.name
        binding.commentText.text = item.comment.content
        binding.like.text = item.comment.likesCount.toString()
        binding.commentCreate.text = "to day"

        binding.authorAvatar.setOnClickListener {
            item.openProfile.invoke()
        }
    }
}