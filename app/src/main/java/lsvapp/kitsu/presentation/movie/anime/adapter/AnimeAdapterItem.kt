package lsvapp.kitsu.presentation.movie.anime.adapter

import lsvapp.kitsu.domain.entity.dto.AnimeDto

data class AnimeAdapterItem(
    val anime: AnimeDto,
    val action: () -> Unit
)