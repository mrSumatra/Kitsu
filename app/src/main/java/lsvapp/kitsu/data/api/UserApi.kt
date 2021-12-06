package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.Data
import lsvapp.kitsu.domain.entity.PageContent
import lsvapp.kitsu.domain.entity.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsers(): PageContent<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Long): Data<UserDto>
}