package lsvapp.kitsu.presentation.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import lsvapp.kitsu.domain.entity.Groupe
import lsvapp.kitsu.domain.interactor.GroupeInteractor
import lsvapp.kitsu.presentation.feed.tab.FeedViewTabModel

class GroupeTabViewModel(
    private val groupeInteractor: GroupeInteractor
) : ViewModel() {

    val groupesPagerFlow: Flow<PagingData<Groupe>> = Pager(
        config = PagingConfig(
            pageSize = FeedViewTabModel.PAGE_SIZE,
            initialLoadSize = FeedViewTabModel.PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope)

    private fun pagingSource() = object : PagingSource<Int, Groupe>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Groupe> {
            return try {
                val groups = groupeInteractor.getGroups()

                val page = params.key ?: 1
                val pageSize = params.loadSize
                LoadResult.Page(
                    data = groups,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (groups.size < pageSize) null else page + 1
                )
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Groupe>): Int? {
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

sealed class GroupeTabState {
    data class Error(val message: String?) : GroupeTabState()
    object Loading : GroupeTabState()
    data class Content(val groups: List<Groupe>) : GroupeTabState()
}