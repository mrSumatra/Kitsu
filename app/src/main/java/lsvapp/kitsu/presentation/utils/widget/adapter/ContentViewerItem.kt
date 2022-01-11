package lsvapp.kitsu.presentation.utils.widget.adapter

import lsvapp.kitsu.domain.entity.dto.AnimeDto

sealed class ContentViewerItem {
    data class Anime(val anime: AnimeDto, val action: () -> Unit) : ContentViewerItem()
    data class Manga(val anime: AnimeDto) : ContentViewerItem()
    data class AllMovie(val title: String, val action: () -> Unit) : ContentViewerItem()
}