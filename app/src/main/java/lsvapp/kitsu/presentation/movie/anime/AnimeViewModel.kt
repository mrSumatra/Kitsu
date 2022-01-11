package lsvapp.kitsu.presentation.movie.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.presentation.feed.tab.FeedViewTabModel
import lsvapp.kitsu.presentation.movie.anime.adapter.AnimeAdapterItem

class AnimeViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _errorEvent = MutableStateFlow<String?>(null)
    val errorEvent: StateFlow<String?> = _errorEvent

    val animePagerFlow: Flow<PagingData<AnimeAdapterItem>> = Pager(
        config = PagingConfig(
            pageSize = FeedViewTabModel.PAGE_SIZE,
            initialLoadSize = FeedViewTabModel.PAGE_SIZE
        ),
        pagingSourceFactory = { pagingSource() }
    ).flow.cachedIn(viewModelScope).map { pagingSource ->
        pagingSource.map {
            it.toAdapterItem()
        }
    }

    private fun pagingSource() = object : PagingSource<Int, AnimeDto>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeDto> {
            return try {
                val animeDto = animeInteractor.getAnime(
                    page = params.key ?: 0,
                    size = params.loadSize
                ).data.map {
                    it.attributes
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

        override fun getRefreshKey(state: PagingState<Int, AnimeDto>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }
    }

    private fun AnimeDto.toAdapterItem() = AnimeAdapterItem(
        anime = this
    ) {}

    companion object {
        const val PAGE_SIZE = 10
    }
}