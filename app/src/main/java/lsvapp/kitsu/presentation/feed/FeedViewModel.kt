package lsvapp.kitsu.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.domain.interactor.PostInteractor

class FeedViewModel(
    private val postInteractor: PostInteractor
) : ViewModel() {

    private val _state = MutableLiveData<FeedState>(FeedState.Loading)
    val state: LiveData<FeedState> = _state

    init {
        initState()
    }

    private fun initState() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            val pageContent = postInteractor.getPosts()
            _state.value = FeedState.Content(
                posts = pageContent.data.map {
                    it.attributes
                }
            )
        }
    }
}