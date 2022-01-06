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
        initComments()
    }

    private fun initComments() {
        _state.value = PostDetailsState.Loading
        viewModelScope.launch {
            try {
                val commentsDto = postInteractor.getComments(id)
                val comments = commentsDto.data.map { commentsDto ->
                    val userDto = async { postInteractor.getAuthorComment(commentsDto.id) }
                    val author = dtoConverter.dataToUser(userDto.await().data)
                    dtoConverter.daraToComment(data = commentsDto, author = author)
                }
                _state.value = PostDetailsState.Content(comments = comments)
            } catch (e: Exception) {
                _state.value = PostDetailsState.Error(message = e.message)
            }
        }
    }
}