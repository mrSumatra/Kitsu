package lsvapp.kitsu.presentation.feed

import lsvapp.kitsu.domain.entity.Post

sealed class FeedState {

    data class Error(val message: String?) : FeedState()
    object Loading : FeedState()
    data class Content(val posts: List<Post>) : FeedState()
}