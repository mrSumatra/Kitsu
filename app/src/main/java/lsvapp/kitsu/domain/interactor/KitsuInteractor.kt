package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.KitsuRepository

class KitsuInteractor(
    private val repository: KitsuRepository
) {
    suspend fun getAnime() = repository.getAnime()
}