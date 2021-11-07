package lsvapp.kitsu.presentation.feed.tab

import lsvapp.kitsu.domain.entity.Post

sealed class FeedTabState {

    data class Error(val message: String?) : FeedTabState()
    object Loading : FeedTabState()
    data class Content(val post: List<Post>) : FeedTabState()
}