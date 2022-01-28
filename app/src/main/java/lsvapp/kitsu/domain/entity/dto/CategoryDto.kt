package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.Image

@JsonClass(generateAdapter = true)
data class CategoryDto(

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "updatedAt")
    val updatedAt: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "totalMediaCount")
    val totalMediaCount: Int,

//    @Json(name = "image")
//    val image: Image

)
