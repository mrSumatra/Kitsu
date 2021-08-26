package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.network.AnimeApi

class AnimeRepository(
    private val animeApi: AnimeApi
) {
    suspend fun getAnime() = animeApi.getAnime()
}