package lsvapp.kitsu.domain.entity

data class AnimeEpisode(
    val id: Long,
    val canonicalTitle: String?,
    val imageLink: String?,
    val description: String?,
    val seasonNumber: String?,
    val number: String?,
    val length: String?
)