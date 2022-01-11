package lsvapp.kitsu.presentation.utils.widget.adapter

sealed class ContentViewerItem {
    data class Content(
        val id: Long? = null,
        val title: String,
        val desc: String? = null,
        val ageRating: String? = null,
        val rating: String? = null,
        val imageLink: String? = null,
        val action: () -> Unit
    ) : ContentViewerItem()

    data class AllMovie(val title: String, val action: () -> Unit) : ContentViewerItem()
}