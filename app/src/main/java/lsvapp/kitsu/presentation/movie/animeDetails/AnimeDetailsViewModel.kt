package lsvapp.kitsu.presentation.movie.animeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.entity.AnimeCategory
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
                val categories = animeInteractor.getAnimeCategories(id = animeId)
                EpisodeState.Content(episodes = episodes, categories = categories)
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                EpisodeState.Error(e.message)
            }
        }
    }
}

sealed class EpisodeState {
    object Loading : EpisodeState()
    data class Content(val episodes: List<AnimeEpisode>, val categories: List<AnimeCategory>) :
        EpisodeState()

    data class Error(val message: String?) : EpisodeState()
}