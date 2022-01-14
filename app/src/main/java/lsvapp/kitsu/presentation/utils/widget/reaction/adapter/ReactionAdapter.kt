package lsvapp.kitsu.presentation.utils.widget.reaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.databinding.ItemReactionBinding

class ReactionAdapter : RecyclerView.Adapter<ReactionAdapterViewHolder>() {

    var items: List<ReactionAdapterItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReactionAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReactionBinding.inflate(inflater, parent, false)
        return ReactionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReactionAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}