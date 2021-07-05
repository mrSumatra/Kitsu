package lsvapp.kitsu.presentation

import lsvapp.kitsu.domain.entity.Anime

sealed class MainState {

    class Error(val message: String?) : MainState()
    object Loading : MainState()
    data class Content(val anime: List<Anime>) : MainState()
}