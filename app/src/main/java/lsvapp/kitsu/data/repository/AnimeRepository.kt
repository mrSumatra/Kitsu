package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.AnimeApi

class AnimeRepository(
    private val animeApi: AnimeApi
) {
    suspend fun getAnime(
        page: Int? = null,
        size: Int? = null,
        seasonYear: String? = null,
        streamers: String? = null,
        season: String? = null,
        category: String? = null,
        sort: String? = null,
    ) =
        animeApi.getAnime(
            page = page,
            size = size,
            seasonYear = seasonYear,
            streamers = streamers,
            season = season,
            category = category,
            sort = sort
        )

    suspend fun getAnimeById(id: Long) = animeApi.getAnimeById(id = id)

    suspend fun getAnimeEpisodes(id: Long) = animeApi.getAnimeEpisodes(id = id)

    suspend fun getAnimeEpisodesById(id: Long) = animeApi.getAnimeEpisodesById(id = id)

    suspend fun getAnimeCategories(id: Long) = animeApi.getAnimeCategories(id = id)

    suspend fun getAnimeReaction(id: Long) = animeApi.getAnimeReaction(id)

    suspend fun getReactionUser(id: Long) = animeApi.getAnimeReactionUser(id)

    suspend fun getCategories() = animeApi.getCategories()
}