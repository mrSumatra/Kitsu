package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(

    @Json(name = "tiny")
    val tiny: String,

    @Json(name = "small")
    val small: String,

    @Json(name = "medium")
    val medium: String,

    @Json(name = "large")
    val large: String,

    @Json(name = "original")
    val original: String,
)




