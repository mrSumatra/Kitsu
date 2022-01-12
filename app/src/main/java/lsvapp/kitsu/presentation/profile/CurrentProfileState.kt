package lsvapp.kitsu.presentation.profile

import lsvapp.kitsu.domain.entity.User

sealed class CurrentProfileState {

    data class Error(val message: String?) : CurrentProfileState()
    object Loading : CurrentProfileState()
    data class Content(val user: User) : CurrentProfileState()
}