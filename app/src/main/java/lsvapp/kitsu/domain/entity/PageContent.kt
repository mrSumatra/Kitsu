package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageContent<T>(

    @Json(name = "data")
    val data: List<Data<T>>

)