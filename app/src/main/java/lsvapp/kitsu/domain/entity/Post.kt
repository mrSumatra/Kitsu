package lsvapp.kitsu.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(

    /**
     * Ид
     */
    val id: Long,

    /**
     * Дата создания
     */
    val createdAt: String,

    /**
     * Автор поста
     */
    val author: User,

    /**
     * Контент
     */
    val content: String,

    /**
     * Кол-во коментариев
     */
    val commentsCount: Int,

    /**
     * Кол-во лайков
     */
    val postLikesCount: Int

) : Parcelable
