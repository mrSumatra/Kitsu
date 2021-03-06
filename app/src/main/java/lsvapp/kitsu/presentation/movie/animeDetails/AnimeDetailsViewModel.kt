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

    private val _infoState = MutableStateFlow<DetailsInfo>(DetailsInfo.Loading)
    val infoState: StateFlow<DetailsInfo> = _infoState

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
                        val author = animeInteractor.getReactionUser(it.id)
                        dtoConverter.dataToAnimeReaction(
                            data = it,
                            author = dtoConverter.dataToUser(author.data)
                        )
                    }
                }
                DetailsInfo.Content(
                    episodes = episodes.await(),
                    categories = categories.await(),
                    reaction = animeReaction.await()
                )
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                DetailsInfo.Error(e.message)
            }
        }
    }

    companion object {
        const val DEFAULT_USER_ID: Long = 30398
    }
}

sealed class DetailsInfo {
    object Loading : DetailsInfo()
    data class Content(
        val episodes: List<AnimeEpisode>,
        val categories: List<AnimeCategory>,
        val reaction: List<AnimeReaction>
    ) :
        DetailsInfo()

    data class Error(val message: String?) : DetailsInfo()
}