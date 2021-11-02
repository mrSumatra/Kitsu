package lsvapp.kitsu.domain.entity

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
    val avatar: Image,

    /**
     * Дата рождения
     */
    val birthday: String?
)
