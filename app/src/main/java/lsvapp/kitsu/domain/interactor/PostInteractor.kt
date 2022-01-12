package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.PostRepository

class PostInteractor(
    private val postRepository: PostRepository
) {
    suspend fun getPosts(page: Int? = null, size: Int? = null) =
        postRepository.getPosts(page = page, size = size)

    suspend fun getPostsById(id: Long) = postRepository.getPostsById(id)

    suspend fun getAuthorPostUser(postId: Long) = postRepository.getAuthorPostUser(postId)

    suspend fun getComments(id: Long) = postRepository.getComments(id)

    suspend fun getAuthorComment(id: Long) = postRepository.getAuthorComment(id)
}