package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeCategoryDto(

    @Json(name = "title")
    val title: String,

    @Json(name = "totalMediaCount")
    val totalMediaCount: String,
)