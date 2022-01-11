package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.AnimeApi

class AnimeRepository(
    private val animeApi: AnimeApi
) {
    suspend fun getAnime(page: Int? = null, size: Int? = null) =
        animeApi.getAnime(page = page, size = size)

    suspend fun getAnimeEpisodes(id: Long) =
        animeApi.getAnimeEpisodes(id = id)
}