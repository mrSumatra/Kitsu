package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.domain.entity.dto.PageList
import retrofit2.http.GET

interface AnimeApi {

    @GET("anime")
    suspend fun getAnime(): PageList<AnimeDto>

}