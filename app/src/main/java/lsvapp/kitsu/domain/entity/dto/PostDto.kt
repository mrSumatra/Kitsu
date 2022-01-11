package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "content")
    val content: String,

    @Json(name = "commentsCount")
    val commentsCount: Int,

    @Json(name = "postLikesCount")
    val postLikesCount: Int

)
