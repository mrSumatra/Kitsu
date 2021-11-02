package lsvapp.kitsu.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor

class FeedViewModel(
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _state = MutableLiveData<FeedState>()
    val state: LiveData<FeedState> = _state

    init {
        initState()
    }

    private fun initState() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            val postsDto = postInteractor.getPosts()
            val posts = postsDto.data.map { post->
                val userDto = async { postInteractor.getAuthorPostUser(post.id) }
                val author = dtoConverter.dataToUser(userDto.await().data)
                dtoConverter.dataToPost(data = post, author = author)
            }
            _state.value = FeedState.Content(post = posts)
        }
    }
}