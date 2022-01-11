package lsvapp.kitsu.presentation.feed.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor

class FeedViewTabModel(
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    val postPagerFlow: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope)

    private fun pagingSource() = object : PagingSource<Int, Post>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
            return try {
                val postsDto = postInteractor.getPosts(
                    page = params.key ?: 0,
                    size = params.loadSize
                )
                val posts = postsDto.data.map { post ->
                    val userDto = postInteractor.getAuthorPostUser(post.id)
                    val author = viewModelScope.async { dtoConverter.dataToUser(userDto.data) }
                    dtoConverter.dataToPost(data = post, author = author.await())
                }
                LoadResult.Page(
                    data = posts,
                    prevKey = params.key?.let { it - 1 },
                    nextKey = if (posts.size < PAGE_SIZE) null else (params.key ?: 0) + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}