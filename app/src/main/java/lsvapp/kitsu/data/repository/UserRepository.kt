package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.UserApi

class UserRepository(
    private val userApi: UserApi
) {
    suspend fun getUsers(page: Int? = null, size: Int? = null) =
        userApi.getUsers(page = page, size = size)

    suspend fun getUserById(id: Long) = userApi.getUserById(id)

}