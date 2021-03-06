package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.AnimeRepository
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.AnimeCategory
import lsvapp.kitsu.domain.entity.AnimeEpisode
import lsvapp.kitsu.domain.entity.utils.DtoConverter

class AnimeInteractor(
    private val repository: AnimeRepository,
    private val dtoConverter: DtoConverter
) {
    suspend fun getAnime(
        page: Int? = null,
        size: Int? = null,
        seasonYear: String? = null,
        streamers: String? = null,
        season: String? = null,
        category: String? = null,
        sort: String? = null,
    ): List<Anime> {
        val animeDto = repository.getAnime(
            page = page,
            size = size,
            seasonYear = seasonYear,
            streamers = streamers,
            season = season,
            category = category,
            sort = sort
        )
        return animeDto.data.map {
            dtoConverter.dataToAnime(it)
        }
    }

    suspend fun getAnimeById(animeId: Long): Anime {
        val animeDto = repository.getAnimeById(animeId)
        return dtoConverter.dataToAnime(animeDto.data)
    }

    suspend fun getAnimeEpisodes(id: Long) = repository.getAnimeEpisodes(id = id).data.map {
        dtoConverter.dataToAnimeEpisode(it)
    }

    suspend fun getAnimeEpisodesById(episodeId: Long): AnimeEpisode {
        val episode = repository.getAnimeEpisodesById(id = episodeId).data
        return dtoConverter.dataToAnimeEpisode(episode)
    }

    suspend fun getAnimeCategories(id: Long): List<AnimeCategory> =
        repository.getAnimeCategories(id = id).data.map {
            dtoConverter.dataToAnimeCategories(it)
        }

    suspend fun getAnimeReaction(id: Long) = repository.getAnimeReaction(id)

    suspend fun getReactionUser(id: Long) = repository.getReactionUser(id)

    suspend fun getCategories() = repository.getCategories()
}