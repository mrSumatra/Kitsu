package lsvapp.kitsu.presentation

import lsvapp.kitsu.domain.entity.Anime

sealed class MainState {

    data class Error(val message: String?) : MainState()
    object Loading : MainState()
    data class Content(val anime: Anime) : MainState()
}