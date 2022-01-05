package lsvapp.kitsu.presentation.feed.postdetails.adapter

import lsvapp.kitsu.domain.entity.Comment

data class CommentItem(
    val comment: Comment,
    val openProfile: () -> Unit
)