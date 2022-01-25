package lsvapp.kitsu.presentation.utils.widget.category

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lsvapp.kitsu.presentation.utils.widget.category.adapter.CategoryAdapter
import lsvapp.kitsu.presentation.utils.widget.category.adapter.CategoryAdapterItem

class AnimeCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val animeCategoryAdapter = CategoryAdapter()

    init {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = HORIZONTAL
        adapter = animeCategoryAdapter
        isNestedScrollingEnabled = false
        layoutManager = linearLayoutManager
        setHasFixedSize(true)
    }

    fun setAdapterItem(items: List<CategoryAdapterItem>) {
        animeCategoryAdapter.items = items
    }
}