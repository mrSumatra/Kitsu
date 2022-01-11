package lsvapp.kitsu.presentation.feed.postdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.CommentItemBinding

class CommentAdapter : PagingDataAdapter<CommentItem, CommentViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else error("Item is null. getItemViewType")
    }

    override fun getItemViewType(position: Int) = R.layout.comment_item

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<CommentItem>() {
            override fun areItemsTheSame(
                oldItem: CommentItem,
                newItem: CommentItem
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CommentItem,
                newItem: CommentItem
            ) = oldItem.comment == newItem.comment && oldItem == newItem

            override fun getChangePayload(
                oldItem: CommentItem,
                newItem: CommentItem
            ): Any? = Any()
        }
    }
}