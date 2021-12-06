package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.Page
import lsvapp.kitsu.domain.entity.PageContent
import lsvapp.kitsu.domain.entity.dto.PostDto
import lsvapp.kitsu.domain.entity.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): PageContent<PostDto>

    @GET("posts/{id}")
    suspend fun getPostsById(@Path("id") id: Long): Page<PostDto>

    @GET("posts/{id}/user")
    suspend fun getAuthorPostUser(@Path("id") id: Long): Page<UserDto>

}