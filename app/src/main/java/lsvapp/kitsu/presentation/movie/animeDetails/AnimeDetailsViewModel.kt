package lsvapp.kitsu.presentation.movie.animeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.entity.AnimeCategory
import lsvapp.kitsu.domain.entity.AnimeEpisode
import lsvapp.kitsu.domain.entity.AnimeReaction
import lsvapp.kitsu.domain.entity.utils.DtoConverter
import lsvapp.kitsu.domain.interactor.AnimeInteractor
import lsvapp.kitsu.domain.interactor.UserInteractor

class AnimeDetailsViewModel(
    val animeId: Long,
    private val animeInteractor: AnimeInteractor,
    private val userInteractor: UserInteractor,
    private val dtoConverter: DtoConverter
) : ViewModel() {

    private val _infoState = MutableStateFlow<EpisodeState>(EpisodeState.Loading)
    val infoState: StateFlow<EpisodeState> = _infoState

    init {
        initState()
    }

    fun initState() {
        viewModelScope.launch {
            _infoState.value = try {
                val episodes = async { animeInteractor.getAnimeEpisodes(id = animeId) }
                val categories = async { animeInteractor.getAnimeCategories(id = animeId) }
                val animeReactionDto = async { animeInteractor.getAnimeReaction(id = animeId) }
                val animeReaction = async {
                    animeReactionDto.await().data.map {
                        val authorId = animeInteractor.getReactionUser(it.id).id ?: DEFAULT_USER_ID
                        val author = userInteractor.getUserById(authorId)
                        dtoConverter.dataToAnimeReaction(it, author = author)
                    }
                }
                EpisodeState.Content(
                    episodes = episodes.await(),
                    categories = categories.await(),
                    reaction = animeReaction.await()
                )
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                EpisodeState.Error(e.message)
            }
        }
    }

    companion object {
        const val DEFAULT_USER_ID: Long = 30398
    }
}

sealed class EpisodeState {
    object Loading : EpisodeState()
    data class Content(
        val episodes: List<AnimeEpisode>,
        val categories: List<AnimeCategory>,
        val reaction: List<AnimeReaction>
    ) :
        EpisodeState()

    data class Error(val message: String?) : EpisodeState()
}