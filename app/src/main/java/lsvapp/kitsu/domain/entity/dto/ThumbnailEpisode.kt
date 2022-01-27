package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailEpisode(

    @Json(name = "original")
    val original: String?
)
