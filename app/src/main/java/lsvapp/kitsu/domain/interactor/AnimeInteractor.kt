package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.AnimeRepository

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnime(page: Int? = null, size: Int? = null) =
        repository.getAnime(page = page, size = size)

    suspend fun getAnimeEpisodes(id: Long) =
        repository.getAnimeEpisodes(id = id)
}