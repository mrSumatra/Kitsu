package lsvapp.kitsu.domain.entity.utils

import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.domain.entity.dto.CommentDto
import lsvapp.kitsu.domain.entity.dto.Data
import lsvapp.kitsu.domain.entity.dto.PostDto
import lsvapp.kitsu.domain.entity.dto.UserDto
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
}