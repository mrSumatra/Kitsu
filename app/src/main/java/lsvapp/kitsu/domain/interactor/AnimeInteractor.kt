package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.AnimeRepository
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.utils.DtoConverter

class AnimeInteractor(
    private val repository: AnimeRepository,
    private val dtoConverter: DtoConverter
) {
    suspend fun getAnime(page: Int? = null, size: Int? = null): List<Anime> {
        val animeDto = repository.getAnime(page = page, size = size)
        return animeDto.data.map {
            dtoConverter.dataToAnime(it)
        }
    }

    suspend fun getAnimeEpisodes(id: Long) =
        repository.getAnimeEpisodes(id = id)
}