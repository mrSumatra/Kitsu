package lsvapp.kitsu.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.AnimeInteractor

class FeedViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _state = MutableLiveData<FeedState>()
    val state: LiveData<FeedState> = _state

    init {
        initState()
    }

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            _state.value = FeedState.Error(exception.message)
        }

    private fun initState() {
        _state.value = FeedState.Loading
        viewModelScope.launch(exceptionHandler) {
            val pageContent = animeInteractor.getAnime()
            _state.value = FeedState.Content(
                anime = pageContent.data.first().attributes
            )
        }
    }
}