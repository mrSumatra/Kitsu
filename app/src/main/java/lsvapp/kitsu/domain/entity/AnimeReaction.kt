package lsvapp.kitsu.domain.entity

data class AnimeReaction(
    val createdAt: String,
    val reaction: String,
    val author: User
)
