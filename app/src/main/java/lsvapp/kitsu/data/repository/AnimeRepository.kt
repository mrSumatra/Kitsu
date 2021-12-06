package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.AnimeApi

class AnimeRepository(
    private val animeApi: AnimeApi
) {
    suspend fun getAnime() = animeApi.getAnime()
}