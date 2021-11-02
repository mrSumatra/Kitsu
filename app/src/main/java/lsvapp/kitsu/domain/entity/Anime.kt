package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.enums.AgeRating

@JsonClass(generateAdapter = true)
data class Anime(

    @Json(name = "canonicalTitle")
    val canonicalTitle: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "youtubeVideoId")
    val youtubeVideoId: String,

    @Json(name = "status")
    val status: String,

    @Json(name = "posterImage")
    val posterImage: Image,

//    @Json(name = "coverImage")
//    val coverImage: Image,

    @Json(name = "ageRating")
    val ageRating: AgeRating,

    @Json(name = "averageRating")
    val averageRating: String,

    @Json(name = "userCount")
    val userCount: Int,

    @Json(name = "favoritesCount")
    val favoritesCount: Int,

    @Json(name = "episodeCount")
    val episodeCount: Int,

//    @Json(name = "nextRelease")
//    val nextRelease: String,
)