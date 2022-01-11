package lsvapp.kitsu.presentation.movie.movietab

import lsvapp.kitsu.domain.entity.dto.AnimeDto

sealed class MovieTabState {

    data class Error(val message: String?) : MovieTabState()
    object Loading : MovieTabState()
    data class Content(val anime: List<AnimeDto>) : MovieTabState()
}