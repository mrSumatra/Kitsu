package lsvapp.kitsu.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PageContent<T>(

    @Json(name = "data")
    val data: List<T>

){

    constructor(data: List<T>, other: PageContent<*>) : this(
        data
    )
}