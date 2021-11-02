package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.network.kitsuAPI.UserApi

class UserRepository(
    private val userApi: UserApi
) {
    suspend fun getUsers() = userApi.getUsers()
    suspend fun getUserById(id: Long) = userApi.getUserById(id)

}