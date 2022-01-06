package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.Page
import lsvapp.kitsu.domain.entity.dto.PageList
import lsvapp.kitsu.domain.entity.dto.CommentDto
import lsvapp.kitsu.domain.entity.dto.PostDto
import lsvapp.kitsu.domain.entity.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPosts(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
    ): PageList<PostDto>

    @GET("posts/{id}")
    suspend fun getPostsById(@Path("id") id: Long): Page<PostDto>

    @GET("posts/{id}/user")
    suspend fun getAuthorPostUser(@Path("id") id: Long): Page<UserDto>

    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") id: Long): PageList<CommentDto>

    @GET("comments/{id}/user")
    suspend fun getAuthorPostComments(@Path("id") id: Long): Page<UserDto>


}