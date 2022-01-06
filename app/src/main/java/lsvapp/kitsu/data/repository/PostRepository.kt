package lsvapp.kitsu.data.repository

import com.dropbox.android.external.store4.*
import lsvapp.kitsu.data.api.PostApi
import lsvapp.kitsu.domain.entity.dto.PageList
import lsvapp.kitsu.domain.entity.dto.PostDto
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

class PostRepository(
    private val postApi: PostApi
) {

    @ExperimentalTime
    private val postStore: Store<PostQuery, PageList<PostDto>> = StoreBuilder
        .from(
            Fetcher.of<PostQuery, PageList<PostDto>> {
                postApi.getPosts(
                    page = it.page,
                    size = it.size
                )
            }
        )
        .cachePolicy(
            MemoryPolicy.builder<PostQuery, PageList<PostDto>>()
                .setExpireAfterWrite(CACHE_TIME.toDuration(DurationUnit.MINUTES))
                .setMaxSize(MAX_SIZE).build()
        )
        .build()

//    @ExperimentalTime
//    suspend fun getPosts(page: Int? = null, size: Int? = null) : PageList<PostDto> {
//        val query = PostQuery(
//            page = page,
//            size = size
//        )
//        return postStore.get(query)
//    }

    suspend fun getPosts(page: Int? = null, size: Int? = null) = postApi.getPosts(
        page = page,
        size = size
    )

    suspend fun getPostsById(id: Long) = postApi.getPostsById(id)

    suspend fun getComments(id: Long) = postApi.getPostComments(id)

    suspend fun getAuthorPostUser(id: Long) = postApi.getAuthorPostUser(id)

    suspend fun getAuthorComment(id: Long) = postApi.getAuthorPostComments(id)

    companion object {
        const val CACHE_TIME = 30
        const val MAX_SIZE: Long = 10
    }
}

data class PostQuery(
    val page: Int? = null,
    val size: Int? = null,
)