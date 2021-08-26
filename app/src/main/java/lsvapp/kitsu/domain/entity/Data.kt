package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data<T>(

    @Json(name = "id")
    val id: Long,

    @Json(name = "type")
    val type: String,

    @Json(name = "attributes")
    val attributes: T

)