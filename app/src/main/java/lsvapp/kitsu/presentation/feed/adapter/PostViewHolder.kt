package lsvapp.kitsu.presentation.feed.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.PostItemBinding

class PostViewHolder(
    private val binding: PostItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PostAdapterItem) {
        binding.authorAvatar.load(item.post.author.avatar.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = item.post.author.name
        binding.postCreate.text = "TEST_CREATE DATE"
        binding.contentText.text = item.post.content
        binding.like.text = item.post.postLikesCount.toString()
        binding.comments.text = item.post.commentsCount.toString()
    }

}