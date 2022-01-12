package lsvapp.kitsu.presentation.movie.animeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.entity.AnimeEpisode
import lsvapp.kitsu.domain.interactor.AnimeInteractor

class AnimeDetailsViewModel(
    val animeId: Long,
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _episodeState = MutableStateFlow<EpisodeState>(EpisodeState.Loading)
    val episodeState: StateFlow<EpisodeState> = _episodeState

    init {
        initState()
    }

    fun initState() {
        viewModelScope.launch {
            _episodeState.value = try {
                val episodes = animeInteractor.getAnimeEpisodes(id = animeId)
                EpisodeState.Content(episodes)
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                EpisodeState.Error(e.message)
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}

sealed class EpisodeState {
    object Loading : EpisodeState()
    data class Content(val episodes: List<AnimeEpisode>) : EpisodeState()
    data class Error(val message: String?) : EpisodeState()
}