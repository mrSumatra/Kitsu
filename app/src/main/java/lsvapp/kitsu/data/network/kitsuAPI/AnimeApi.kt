package lsvapp.kitsu.data.network.kitsuAPI

import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.PageContent
import retrofit2.http.GET

interface AnimeApi {

    @GET("anime")
    suspend fun getAnime(): PageContent<Anime>

}