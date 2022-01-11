package lsvapp.kitsu.domain.entity.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.format.DateTimeFormatter

class InstantTimeJsonAdapter {

    private val serverFormat = DateTimeFormatter.ISO_INSTANT

    @ToJson
    fun toJson(instantTime: Instant): String = serverFormat.format(instantTime)

    @FromJson
    fun fromJson(src: String): Instant = Instant.parse(src)
}