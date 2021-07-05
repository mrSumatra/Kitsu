package lsvapp.kitsu.data.network

import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.PageContent
import retrofit2.http.GET

interface AnimeAPI {

    @GET("anime")
    suspend fun getAnime(): PageContent<Anime>

}