package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.AnimeRepository

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnime() = repository.getAnime()
}