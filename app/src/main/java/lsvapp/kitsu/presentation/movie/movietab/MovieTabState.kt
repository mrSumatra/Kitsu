package lsvapp.kitsu.presentation.movie.movietab

import lsvapp.kitsu.domain.entity.Anime

sealed class MovieTabState {

    data class Error(val message: String?) : MovieTabState()
    object Loading : MovieTabState()
    data class Content(
        val anime: List<Anime>,
        val actualAnime: List<Anime>,
        val animeByAmazon: List<Anime>
    ) : MovieTabState()
}