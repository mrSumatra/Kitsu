package lsvapp.kitsu.presentation.movie.episcodeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.entity.AnimeEpisode
import lsvapp.kitsu.domain.interactor.AnimeInteractor

class AnimeEpisodeViewModel(
    val animeId: Long,
    val episodeId: Long,
    private val animeInteractor: AnimeInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<EpisodeDetailsState>(EpisodeDetailsState.Loading)
    val state: StateFlow<EpisodeDetailsState> = _state

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _state.value = try {
                val imageLink = animeInteractor.getAnimeById(animeId).posterImage.original
                val episode = animeInteractor.getAnimeEpisodesById(episodeId = episodeId)
                EpisodeDetailsState.Content(
                    episode = episode,
                    imageLink = episode.imageLink ?: imageLink
                )
            } catch (e: Exception) {
                EpisodeDetailsState.Error(message = e.message)
            }
        }
    }
}

sealed class EpisodeDetailsState {
    data class Error(val message: String?) : EpisodeDetailsState()
    object Loading : EpisodeDetailsState()
    data class Content(val episode: AnimeEpisode, val imageLink: String?) : EpisodeDetailsState()
}