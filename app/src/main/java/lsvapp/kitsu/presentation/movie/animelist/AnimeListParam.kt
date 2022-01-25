package lsvapp.kitsu.presentation.movie.animelist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeListParam(
    val title: String? = null,
    val seasonYear: String? = null,
    val streamers: String? = null,
    val ageRating: String? = null,
    val season: String? = null,
    val category: String? = null

) : Parcelable
