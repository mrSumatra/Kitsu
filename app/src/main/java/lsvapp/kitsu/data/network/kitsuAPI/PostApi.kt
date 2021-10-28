package lsvapp.kitsu.data.network.kitsuAPI

import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.PageContent
import lsvapp.kitsu.domain.entity.Post
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): PageContent<Post>

}