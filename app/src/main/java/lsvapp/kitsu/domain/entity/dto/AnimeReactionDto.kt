package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeReactionDto(

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "reaction")
    val reaction: String

)
