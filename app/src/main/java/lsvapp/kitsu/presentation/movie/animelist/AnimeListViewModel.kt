package lsvapp.kitsu.presentation.movie.animelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.presentation.feed.tab.FeedViewTabModel

class AnimeListViewModel(
    private val animeInteractor: AnimeInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _errorEvent = MutableStateFlow<String?>(null)
    val errorEvent: StateFlow<String?> = _errorEvent

    val animeListPagerFlow: Flow<PagingData<Anime>> = Pager(
        config = PagingConfig(
            pageSize = FeedViewTabModel.PAGE_SIZE,
            initialLoadSize = FeedViewTabModel.PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope)

    private fun pagingSource() = object : PagingSource<Int, Anime>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
            return try {
                val animeDto = animeInteractor.getAnime(
                    page = params.key ?: 0,
                    size = params.loadSize
                ).data.map {
                    dtoConverter.dataToAnime(it)
                }
                LoadResult.Page(
                    data = animeDto,
                    prevKey = params.key?.let { it - 1 },
                    nextKey = (params.key ?: 0) + 1
                )
            } catch (e: Exception) {
                _errorEvent.value = e.message
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
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