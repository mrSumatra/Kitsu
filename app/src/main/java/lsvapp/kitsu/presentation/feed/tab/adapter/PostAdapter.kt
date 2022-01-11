package lsvapp.kitsu.presentation.feed.tab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.PostItemBinding

class PostAdapter : PagingDataAdapter<PostAdapterItem, PostViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else error("Item is null. getItemViewType")
    }

    override fun getItemViewType(position: Int) = R.layout.post_item

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<PostAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: PostAdapterItem,
                newItem: PostAdapterItem
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PostAdapterItem,
                newItem: PostAdapterItem
            ) = oldItem.post == newItem.post && oldItem == newItem

            override fun getChangePayload(
                oldItem: PostAdapterItem,
                newItem: PostAdapterItem
            ): Any? = Any()
        }
    }
}