package lsvapp.kitsu.presentation.movie.movietab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
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
                val anime = async { animeInteractor.getAnime() }
                val actualAnime = async { animeInteractor.getAnime(seasonYear = "2022") }
                val animeByAmazon = async { animeInteractor.getAnime(streamers = "Amazon") }
                MovieTabState.Content(
                    anime = anime.await(),
                    actualAnime = actualAnime.await(),
                    animeByAmazon = animeByAmazon.await()
                )
            } catch (e: Exception) {
                MovieTabState.Error(e.message)
            }
        }
    }

}