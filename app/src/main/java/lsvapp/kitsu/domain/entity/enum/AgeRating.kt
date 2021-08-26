package lsvapp.kitsu.domain.entity.enum

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = false)
enum class AgeRating {

    /**
     * General Audiences
     */
    G,

    /**
     * Parental Guidance Suggested
     */

    PG,

    /**
     * Restricted
     */
    R,

    /**
     * Explicit
     */
    R18
}