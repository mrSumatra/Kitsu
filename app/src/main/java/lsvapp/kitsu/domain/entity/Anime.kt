package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.enum.AgeRating

@JsonClass(generateAdapter = true)
data class Anime (

    @Json(name = "canonicalTitle")
    val canonicalTitle: String,

    @Json(name = "ageRating")
    val ageRating: AgeRating,

    @Json(name = "averageRating")
    val averageRating: String,

    @Json(name = "episodeCount")
    val episodeCount: Int

)