package lsvapp.kitsu.presentation.movie.movietab

import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.dto.CategoryDto

sealed class MovieTabState {

    data class Error(val message: String?) : MovieTabState()
    object Loading : MovieTabState()
    data class Content(
        val anime: List<Anime>,
        val novelties: List<Anime>,
        val actualAnime: List<Anime>,
        val animeByAmazon: List<Anime>,
        val categories: List<CategoryDto>
    ) : MovieTabState()
}