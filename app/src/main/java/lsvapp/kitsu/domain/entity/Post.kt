package lsvapp.kitsu.domain.entity

data class Post(

    /**
     * Ид
     */
    val id: Long,

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

)
