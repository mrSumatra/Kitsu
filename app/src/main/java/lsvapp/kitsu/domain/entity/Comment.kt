package lsvapp.kitsu.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(

    /**
     * Ид
     */
    val id: Long,

    /**
     * Текст комментария
     */
    val content: String,

    /**
     * Кол-во лайков
     */
    val likesCount: Int,

    /**
     * Автор комментария
     */
    val author: User

) : Parcelable