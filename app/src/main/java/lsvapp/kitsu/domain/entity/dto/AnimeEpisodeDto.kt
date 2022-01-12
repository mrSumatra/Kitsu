package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeEpisodeDto(

    @Json(name = "canonicalTitle")
    val canonicalTitle: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "seasonNumber")
    val seasonNumber: String,

    @Json(name = "number")
    val number: String,

    @Json(name = "length")
    val length: String

)
