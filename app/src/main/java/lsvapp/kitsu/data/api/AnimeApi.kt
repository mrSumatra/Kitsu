package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.domain.entity.dto.PageList
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {

    @GET("anime")
    suspend fun getAnime(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
    ): PageList<AnimeDto>

}