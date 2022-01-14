package lsvapp.kitsu.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import lsvapp.kitsu.domain.entity.enums.AgeRating

@Parcelize
data class Anime(

    val id: Long,

    val canonicalTitle: String,

    val startDate: String?,

    val endDate: String?,

    val description: String?,

    val youtubeVideoId: String?,

    val status: String?,

    val posterImage: Image,

    val coverImage: Image?,

    val ageRating: AgeRating?,

    val averageRating: String?,

    val popularityRank: Int,

    val userCount: Int?,

    val favoritesCount: Int?,

    val episodeCount: Int?,

    val episodeLength: Int?

) : Parcelable