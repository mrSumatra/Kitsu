package lsvapp.kitsu.presentation.feed.postdetails

import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.Post

sealed class PostDetailsState {

    data class Error(val message: String?) : PostDetailsState()
    object Loading : PostDetailsState()
    data class Content(val comments: List<Comment>) : PostDetailsState()
}