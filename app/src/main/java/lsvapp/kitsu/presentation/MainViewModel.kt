package lsvapp.kitsu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.KitsuInteractor

class MainViewModel(
    private val kitsuInteractor: KitsuInteractor
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state


    init {
        initState()
    }

    private fun initState() {
        _state.value = MainState.Loading
        viewModelScope.launch {
//            try {
                val animeList = kitsuInteractor.getAnime()

                _state.value = MainState.Content(
                    anime = animeList.data
                )
//            } catch (e: Throwable) {
//                _state.value = MainState.Error(e.message)
//            }
        }
    }
}