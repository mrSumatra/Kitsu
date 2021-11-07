package lsvapp.kitsu.data.repository

import com.dropbox.android.external.store4.*
import lsvapp.kitsu.data.network.kitsuAPI.PostApi
import lsvapp.kitsu.domain.entity.PageContent
import lsvapp.kitsu.domain.entity.dto.PostDto

class PostRepository(
    private val postApi: PostApi
) {

    private val postStore: Store<PostQuery, PageContent<PostDto>> = StoreBuilder
        .from(
            Fetcher.of<PostQuery, PageContent<PostDto>> {
                postApi.getPosts()
            }
        )
        .build()

    suspend fun getPosts() = postStore.get(PostQuery())

    suspend fun getPostsById(id: Long) = postApi.getPostsById(id)

    suspend fun getAuthorPostUser(id: Long) = postApi.getAuthorPostUser(id)

}

data class PostQuery(
    val page: Int? = null,
    val size: Int? = null,
    val id: Long? = null
)