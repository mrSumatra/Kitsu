package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.AnimeApi

class AnimeRepository(
    private val animeApi: AnimeApi
) {
    suspend fun getAnime(page: Int? = null, size: Int? = null) =
        animeApi.getAnime(page = page, size = size)

    suspend fun getAnimeById(id: Long) = animeApi.getAnimeById(id = id)

    suspend fun getAnimeEpisodes(id: Long) = animeApi.getAnimeEpisodes(id = id)

    suspend fun getAnimeEpisodesById(id: Long) = animeApi.getAnimeEpisodesById(id = id)

    suspend fun getAnimeCategories(id: Long) = animeApi.getAnimeCategories(id = id)
}