package lsvapp.kitsu.presentation.movie.movietab.novelties

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.databinding.ItemNoveltiesBinding

class NoveltiesAdapter : RecyclerView.Adapter<NoveltiesAdapterViewHolder>() {

    var items: List<NoveltiesAdapterItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoveltiesAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoveltiesBinding.inflate(inflater, parent, false)
        return NoveltiesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoveltiesAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}