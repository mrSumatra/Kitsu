package lsvapp.kitsu.domain.entity.enums

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = false)
enum class PrivacyType {
    OPEN,
    CLOSED,
    RESTRICTED
}