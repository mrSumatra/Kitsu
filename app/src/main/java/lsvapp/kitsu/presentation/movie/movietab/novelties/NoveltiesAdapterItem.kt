package lsvapp.kitsu.presentation.movie.movietab.novelties

import lsvapp.kitsu.domain.entity.Anime

data class NoveltiesAdapterItem(
    val anime: Anime,
    val action: () -> Unit
)
