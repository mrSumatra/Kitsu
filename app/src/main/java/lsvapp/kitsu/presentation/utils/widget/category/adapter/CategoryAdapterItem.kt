package lsvapp.kitsu.presentation.utils.widget.category.adapter

data class CategoryAdapterItem(
    val category: String,
    val action: () -> Unit
)