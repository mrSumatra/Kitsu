package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.network.AnimeAPI

class KitsuRepository(
    private val animeAPI: AnimeAPI
) {
    suspend fun getAnime() = animeAPI.getAnime()
}