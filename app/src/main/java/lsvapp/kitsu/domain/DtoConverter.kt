package lsvapp.kitsu.domain

import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.dto.Data
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.domain.entity.dto.CommentDto
import lsvapp.kitsu.domain.entity.dto.PostDto
import lsvapp.kitsu.domain.entity.dto.UserDto

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
        content = data.attributes.content,
        commentsCount = data.attributes.commentsCount,
        postLikesCount = data.attributes.postLikesCount,
        author = author
    )

    fun daraToComment(data: Data<CommentDto>, author: User) = Comment(
        id = data.id,
        content = data.attributes.content,
        likesCount = data.attributes.likesCount,
        author = author
    )
}