package lsvapp.kitsu.domain.entity.utils

import com.squareup.moshi.FromJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataTimeJsonAdapter {

    private val serverFormat = DateTimeFormatter.ISO_INSTANT

    @FromJson
    fun fromJson(src: String): LocalDateTime = LocalDateTime.parse(src, serverFormat)
}