package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.Image

@JsonClass(generateAdapter = true)
data class UserDto(

    @Json(name = "name")
    val name: String,

    @Json(name = "about")
    val about: String?,

    @Json(name = "gender")
    val gender: String?,

    @Json(name = "avatar")
    val avatar: Image?,

    @Json(name = "coverImage")
    val coverImage: Image?,

    @Json(name = "birthday")
    val birthday: String?,

    @Json(name = "location")
    val location: String?
)
