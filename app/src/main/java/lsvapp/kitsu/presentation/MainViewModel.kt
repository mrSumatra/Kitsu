package lsvapp.kitsu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import kotlin.Exception

class MainViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state


    init {
        initState()
    }

    private fun initState() {
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                val pageContent = animeInteractor.getAnime()
                _state.value = MainState.Content(
                    anime = pageContent.data.first().attributes
                )
            } catch (e: Exception) {
                _state.value = MainState.Error(e.message)
            }
        }
    }
}