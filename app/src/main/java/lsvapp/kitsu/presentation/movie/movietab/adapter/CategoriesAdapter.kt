package lsvapp.kitsu.presentation.movie.movietab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.databinding.CategoryItemBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapterViewHolder>() {

    var items: List<CategoriesAdapterItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoriesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapterViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size
}