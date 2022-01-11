package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.UserRepository

class UserInteractor(
    private val userRepository: UserRepository
) {
    suspend fun getUsers(page: Int? = null, size: Int? = null) =
        userRepository.getUsers(page = page, size = size)

    suspend fun getUserById(id: Long) = userRepository.getUserById(id)
}