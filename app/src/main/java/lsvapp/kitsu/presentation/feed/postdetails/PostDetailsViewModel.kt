package lsvapp.kitsu.presentation.feed.postdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor

class PostDetailsViewModel(
    val id: Long,
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _state = MutableLiveData<PostDetailsState>()
    val state: LiveData<PostDetailsState> = _state

    init {
        initState()
    }

    private fun initState() {
        _state.value = PostDetailsState.Loading
        viewModelScope.launch {
            try {
                val postsDto = postInteractor.getPostsById(id)
                val authorDto = postInteractor.getAuthorPostUser(id)
                val author = dtoConverter.dataToUser(data = authorDto.data)
                val post = dtoConverter.dataToPost(data = postsDto.data, author = author)
                _state.value = PostDetailsState.Content(post = post)
            } catch (e: Exception) {
                _state.value = PostDetailsState.Error(message = e.message)
            }
        }
    }
}