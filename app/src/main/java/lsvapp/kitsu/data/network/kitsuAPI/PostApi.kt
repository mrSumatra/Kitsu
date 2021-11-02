package lsvapp.kitsu.data.network.kitsuAPI

import lsvapp.kitsu.domain.entity.Data
import lsvapp.kitsu.domain.entity.Page
import lsvapp.kitsu.domain.entity.PageContent
import lsvapp.kitsu.domain.entity.dto.PostDto
import lsvapp.kitsu.domain.entity.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): PageContent<PostDto>

    @GET("posts/{id}/user")
    suspend fun getAuthorPostUser(@Path("id") id: Long): Page<UserDto>

}