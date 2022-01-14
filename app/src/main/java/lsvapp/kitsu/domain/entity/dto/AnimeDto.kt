package lsvapp.kitsu.domain.entity.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import lsvapp.kitsu.domain.entity.Image
import lsvapp.kitsu.domain.entity.enums.AgeRating

@JsonClass(generateAdapter = true)
@Parcelize
data class AnimeDto(

    @Json(name = "canonicalTitle")
    val canonicalTitle: String,

    @Json(name = "startDate")
    val startDate: String?,

    @Json(name = "endDate")
    val endDate: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "youtubeVideoId")
    val youtubeVideoId: String?,

    @Json(name = "status")
    val status: String?,

    @Json(name = "posterImage")
    val posterImage: Image,

    @Json(name = "coverImage")
    val coverImage: Image?,

    @Json(name = "ageRating")
    val ageRating: AgeRating?,

    @Json(name = "averageRating")
    val averageRating: String?,

    @Json(name = "popularityRank")
    val popularityRank: Int,

    @Json(name = "userCount")
    val userCount: Int?,

    @Json(name = "favoritesCount")
    val favoritesCount: Int?,

    @Json(name = "episodeCount")
    val episodeCount: Int?,

    @Json(name = "episodeLength")
    val episodeLength: Int?

) : Parcelable