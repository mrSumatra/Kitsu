package lsvapp.kitsu.presentation.movie.movietab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.AnimeInteractor

class MovieTabViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _animeState = MutableStateFlow<MovieTabState>(MovieTabState.Loading)
    val animeState: StateFlow<MovieTabState> = _animeState

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _animeState.value = try {
                val pageContent = animeInteractor.getAnime(page = 0, size = PAGE_SIZE)
                val anime = pageContent.data.map { it.attributes }
                MovieTabState.Content(anime = anime)
            } catch (e: Exception) {
                MovieTabState.Error(e.message)
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}