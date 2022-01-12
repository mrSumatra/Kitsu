package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lsvapp.kitsu.domain.entity.Image

@JsonClass(generateAdapter = true)

data class GroupeDto(

    @Json(name = "about")
    val about: String,

    @Json(name = "locale")
    val locale: String?,

    @Json(name = "membersCount")
    val membersCount: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "avatar")
    val avatar: Image?,

)