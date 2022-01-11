package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.PostApi

class PostRepository(
    private val postApi: PostApi
) {

    suspend fun getPosts(page: Int? = null, size: Int? = null) = postApi.getPosts(
        page = page,
        size = size
    )

    suspend fun getPostsById(id: Long) = postApi.getPostsById(id)

    suspend fun getComments(id: Long) = postApi.getPostComments(id)

    suspend fun getAuthorPostUser(id: Long) = postApi.getAuthorPostUser(id)

    suspend fun getAuthorComment(id: Long) = postApi.getAuthorPostComments(id)

}