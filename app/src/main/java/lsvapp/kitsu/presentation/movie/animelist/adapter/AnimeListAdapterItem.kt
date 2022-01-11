package lsvapp.kitsu.presentation.movie.animelist.adapter

import lsvapp.kitsu.domain.entity.Anime

data class AnimeListAdapterItem(
    val anime: Anime,
    val action: () -> Unit
)