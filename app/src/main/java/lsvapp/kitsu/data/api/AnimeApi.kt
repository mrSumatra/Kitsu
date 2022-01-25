package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("anime")
    suspend fun getAnime(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
        @Query("filter[seasonYear]") seasonYear: String? = null,
        @Query("filter[streamers]") streamers: String? = null,
        @Query("filter[season]") season: String? = null,
        @Query("filter[categories]") category: String? = null,
    ): PageList<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Long): Page<AnimeDto>

    @GET("anime/{id}/episodes")
    suspend fun getAnimeEpisodes(@Path("id") id: Long): PageList<AnimeEpisodeDto>

    @GET("episodes/{id}")
    suspend fun getAnimeEpisodesById(@Path("id") id: Long): Page<AnimeEpisodeDto>

    @GET("anime/{id}/categories")
    suspend fun getAnimeCategories(@Path("id") id: Long): PageList<AnimeCategoryDto>

    @GET("media-reactions")
    suspend fun getAnimeReaction(@Query("filter[animeId]") id: Long): PageList<AnimeReactionDto>

    @GET("media-reactions/{id}/relationships/user")
    suspend fun getAnimeReactionUser(@Path("id") id: Long): DataShort

}