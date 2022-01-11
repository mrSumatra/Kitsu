package lsvapp.kitsu.presentation.feed.tab

import lsvapp.kitsu.domain.entity.Post

sealed class OpenEvent {
    data class OpenDetails(val post: Post) : OpenEvent()
    data class OpenProfile(val id: Long) : OpenEvent()
}
