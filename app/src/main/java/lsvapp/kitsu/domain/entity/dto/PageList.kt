package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageList<T>(

    @Json(name = "data")
    val data: List<Data<T>>,

    @Json(name = "meta")
    val meta: MetaDto

)