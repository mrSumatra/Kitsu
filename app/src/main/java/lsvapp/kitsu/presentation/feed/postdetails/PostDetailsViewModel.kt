package lsvapp.kitsu.presentation.feed.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor
import lsvapp.kitsu.presentation.feed.tab.FeedViewTabModel

class PostDetailsViewModel(
    val id: Long,
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    val commentPagerFlow: Flow<PagingData<Comment>> = Pager(
        config = PagingConfig(
            pageSize = FeedViewTabModel.PAGE_SIZE,
            initialLoadSize = FeedViewTabModel.PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope)

    private fun pagingSource() = object : PagingSource<Int, Comment>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
            return try {
                val commentsDto = postInteractor.getComments(id)
                val comments = commentsDto.data.map { commentsDto ->
                    val userDto =
                        viewModelScope.async { postInteractor.getAuthorComment(commentsDto.id) }
                    val author = dtoConverter.dataToUser(userDto.await().data)
                    dtoConverter.daraToComment(data = commentsDto, author = author)
                }
                LoadResult.Page(
                    data = comments,
                    prevKey = params.key?.let { it - 1 },
                    nextKey = if (comments.size < FeedViewTabModel.PAGE_SIZE) null else (params.key
                        ?: 0) + 1
                )
            } catch (e: Exception) {
                println("Error = ${e.message}")
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }
    }

    fun addComment() {

    }
}