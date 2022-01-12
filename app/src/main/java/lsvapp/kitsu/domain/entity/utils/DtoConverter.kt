package lsvapp.kitsu.domain.entity.utils

import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.domain.entity.dto.*
import lsvapp.kitsu.presentation.utils.toHumanDataTime

class DtoConverter {

    fun dataToUser(data: Data<UserDto>): User = User(
        id = data.id,
        name = data.attributes.name,
        avatar = data.attributes.avatar,
        about = data.attributes.about,
        gender = data.attributes.gender,
        birthday = data.attributes.birthday
    )

    fun dataToPost(data: Data<PostDto>, author: User) = Post(
        id = data.id,
        createdAt = data.attributes.createdAt.toHumanDataTime(),
        content = data.attributes.content,
        commentsCount = data.attributes.commentsCount,
        postLikesCount = data.attributes.postLikesCount,
        author = author
    )

    fun daraToComment(data: Data<CommentDto>, author: User) = Comment(
        id = data.id,
        createdAt = data.attributes.createdAt.toHumanDataTime(),
        content = data.attributes.content,
        likesCount = data.attributes.likesCount,
        author = author
    )

    fun dataToAnime(data: Data<AnimeDto>) = Anime(
        id = data.id,
        canonicalTitle = data.attributes.canonicalTitle,
        startDate = data.attributes.startDate,
        endDate = data.attributes.endDate,
        description = data.attributes.description,
        youtubeVideoId = data.attributes.youtubeVideoId,
        status = data.attributes.status,
        posterImage = data.attributes.posterImage,
        coverImage = data.attributes.coverImage,
        ageRating = data.attributes.ageRating,
        averageRating = data.attributes.averageRating,
        popularityRank = data.attributes.popularityRank,
        userCount = data.attributes.userCount,
        favoritesCount = data.attributes.favoritesCount,
        episodeCount = data.attributes.episodeCount,
        episodeLength = data.attributes.episodeLength
    )
}