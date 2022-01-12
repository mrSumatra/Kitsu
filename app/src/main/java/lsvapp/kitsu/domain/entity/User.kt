package lsvapp.kitsu.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    /**
     * Ид
     */
    val id: Long,

    /**
     * Имя
     */
    val name: String,

    /**
     * Информация
     */
    val about: String?,

    /**
     * Пол
     */
    val gender: String?,

    /**
     * Аватар
     */
    val avatar: Image?,

    /**
     * Аватар
     */
    val coverImage: Image?,

    /**
     * Дата рождения
     */
    val birthday: String?,

    /**
     * Локация
     */
    val location: String?,

    /**
     * Подписчики
     */
    val followersCount: String?,

    /**
     * Подписки
     */
    val followingCount: String?,

    /**
     * Лайки
     */
    val likesGivenCount: String?


) : Parcelable
