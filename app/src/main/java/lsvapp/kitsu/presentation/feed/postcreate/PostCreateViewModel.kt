package lsvapp.kitsu.presentation.feed.postcreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.domain.interactor.UserInteractor

class PostCreateViewModel(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _createPostState = MutableStateFlow<PostCreateState>(PostCreateState.LoadingProfile)
    val createPostState: StateFlow<PostCreateState> = _createPostState

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _createPostState.value = try {
                val user = userInteractor.getCurrentUser()
                delay(SERVER_RESPONSE)
                PostCreateState.CurrentProfile(user = user)
            } catch (e: Exception) {
                PostCreateState.Error(e.message)
            }
        }
    }

    fun sendPost(text: String) {
        viewModelScope.launch {
            try {//send post =)
                _createPostState.value = PostCreateState.LoadingPost
                delay(SERVER_RESPONSE)
                _createPostState.value = PostCreateState.sendPostSuccessful
            } catch (e: Exception) {
                _createPostState.value = PostCreateState.Error(e.message)
            }
        }
    }

    companion object {
        const val SERVER_RESPONSE: Long = 2000
    }
}

sealed class PostCreateState {
    object LoadingProfile : PostCreateState()
    object LoadingPost : PostCreateState()
    object sendPostSuccessful : PostCreateState()
    data class CurrentProfile(val user: User) : PostCreateState()
    data class Error(val message: String?) : PostCreateState()
}