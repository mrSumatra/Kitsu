package lsvapp.kitsu.presentation.feed.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor
import lsvapp.kitsu.presentation.feed.tab.adapter.PostAdapterItem

class FeedViewTabModel(
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _state = MutableLiveData<FeedTabState>()
    val state: LiveData<FeedTabState> = _state

    private val _openEvent = MutableLiveData<OpenEvent>()
    val openEvent: LiveData<OpenEvent> = _openEvent

    val postPagerFlow: Flow<PagingData<PostAdapterItem>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope).map { pagingSource ->
        pagingSource.map {
            it.toPostAdapterItem()
        }
    }

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

    private fun openPost(post: Post) {
        _openEvent.value = OpenEvent.OpenDetails(post)
    }

    private fun openProfile(id: Long) {
        _openEvent.value = OpenEvent.OpenProfile(id)
    }

    private fun Post.toPostAdapterItem() = PostAdapterItem(
        post = this,
        openProfile = { openProfile(this.author.id) },
        openPost = { openPost(this) }
    )

    companion object {
        const val PAGE_SIZE = 10
    }
}