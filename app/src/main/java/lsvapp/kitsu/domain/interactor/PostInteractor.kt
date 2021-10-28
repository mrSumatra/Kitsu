package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.PostRepository

class PostInteractor(
    private val postRepository: PostRepository
) {
    suspend fun getPosts() = postRepository.getPosts()
}