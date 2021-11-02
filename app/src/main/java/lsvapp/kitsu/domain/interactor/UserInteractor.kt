package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.UserRepository

class UserInteractor(
    private val userRepository: UserRepository
) {
    suspend fun getUser() = userRepository.getUser()
    suspend fun getUserById(id: Long) = userRepository.getUserById(id)
}