package lsvapp.kitsu.presentation.feed.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.DtoConverter
import lsvapp.kitsu.domain.interactor.PostInteractor
import kotlin.time.ExperimentalTime

@ExperimentalTime
class FeedViewTabModel(
    private val postInteractor: PostInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _state = MutableLiveData<FeedTabState>()
    val state: LiveData<FeedTabState> = _state

    init {
        initState()
    }

    private fun initState() {
        _state.value = FeedTabState.Loading
        viewModelScope.launch {
            val postsDto = postInteractor.getPosts(page = 0, size = 1)
            val posts = postsDto.data.map { post ->
                val userDto = async { postInteractor.getAuthorPostUser(post.id) }
                val author = dtoConverter.dataToUser(userDto.await().data)
                dtoConverter.dataToPost(data = post, author = author)
            }
            _state.value = FeedTabState.Content(post = posts)
        }
    }
}