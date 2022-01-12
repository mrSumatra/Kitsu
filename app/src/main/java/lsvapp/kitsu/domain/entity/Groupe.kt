package lsvapp.kitsu.domain.entity


data class Groupe(
    val id: Long,
    val about: String,
    val locale: String?,
    val membersCount: String?,
    val name: String?,
    val avatar: Image?,
)