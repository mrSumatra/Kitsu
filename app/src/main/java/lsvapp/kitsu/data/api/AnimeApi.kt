package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.domain.entity.dto.AnimeEpisodeDto
import lsvapp.kitsu.domain.entity.dto.Page
import lsvapp.kitsu.domain.entity.dto.PageList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("anime")
    suspend fun getAnime(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
    ): PageList<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Long): Page<AnimeDto>

    @GET("anime/{id}/episodes")
    suspend fun getAnimeEpisodes(@Path("id") id: Long): PageList<AnimeEpisodeDto>

    @GET("episodes/{id}")
    suspend fun getAnimeEpisodesById(@Path("id") id: Long): Page<AnimeEpisodeDto>

//
//    @GET("anime/{id}/categories")
//    suspend fun getAnimeCategories(
//        @Path("id") id: Long
//    ): PageList<AnimeEpisodeDto>

}