package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page<T>(
    @Json(name = "data")
    val data: Data<T>
)
