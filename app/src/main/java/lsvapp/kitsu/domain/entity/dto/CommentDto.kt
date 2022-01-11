package lsvapp.kitsu.domain.entity.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant

@JsonClass(generateAdapter = true)
data class CommentDto(

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "content")
    val content: String,

    @Json(name = "likesCount")
    val likesCount: Int

)