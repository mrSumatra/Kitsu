package lsvapp.kitsu.presentation.feed.postdetails


sealed class PostDetailsEvent {
    data class OpenProfile(val profileId: Long) : PostDetailsEvent()
}