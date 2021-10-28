package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.network.kitsuAPI.PostApi

class PostRepository(
    private val postApi: PostApi
) {
    suspend fun getPosts() = postApi.getPosts()
}