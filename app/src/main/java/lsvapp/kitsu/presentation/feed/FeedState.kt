package lsvapp.kitsu.presentation.feed

import lsvapp.kitsu.domain.entity.Anime

sealed class FeedState {

    data class Error(val message: String?) : FeedState()
    object Loading : FeedState()
    data class Content(val anime: Anime) : FeedState()
}