package lsvapp.kitsu.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.AnimeInteractor

class MovieTabViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _state = MutableLiveData<MovieTabState>(MovieTabState.Loading)
    val state: LiveData<MovieTabState> = _state

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _state.value = try {
                val pageContent = animeInteractor.getAnime()
                val animes = pageContent.data.map { it.attributes }
                MovieTabState.Content(anime = animes)
            } catch (e: Exception) {
                MovieTabState.Error(e.message)
            }
        }
    }

}