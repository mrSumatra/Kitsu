package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.Page
import lsvapp.kitsu.domain.entity.dto.PageList
import lsvapp.kitsu.domain.entity.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
    ): PageList<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Long): Page<UserDto>
}