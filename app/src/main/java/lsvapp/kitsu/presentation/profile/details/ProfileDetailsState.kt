package lsvapp.kitsu.presentation.profile.details

import lsvapp.kitsu.domain.entity.User

sealed class ProfileDetailsState {

    data class Error(val message: String?) : ProfileDetailsState()
    object Loading : ProfileDetailsState()
    data class Content(val user: User) : ProfileDetailsState()
}