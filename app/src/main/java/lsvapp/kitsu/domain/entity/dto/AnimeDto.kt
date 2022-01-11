package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.Image
import lsvapp.kitsu.domain.entity.enums.AgeRating

@JsonClass(generateAdapter = true)
data class AnimeDto(

    @Json(name = "canonicalTitle")
    val canonicalTitle: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "youtubeVideoId")
    val youtubeVideoId: String?,

    @Json(name = "status")
    val status: String?,

    @Json(name = "posterImage")
    val posterImage: Image,

    @Json(name = "ageRating")
    val ageRating: AgeRating?,

    @Json(name = "averageRating")
    val averageRating: String,

    @Json(name = "popularityRank")
    val popularityRank: Int,

    @Json(name = "userCount")
    val userCount: Int?,

    @Json(name = "favoritesCount")
    val favoritesCount: Int?,

    @Json(name = "episodeCount")
    val episodeCount: Int?
)