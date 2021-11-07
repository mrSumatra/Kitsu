package lsvapp.kitsu.presentation.feed.tab.adapter

import lsvapp.kitsu.domain.entity.Post

data class PostAdapterItem(
    val post: Post,
    val openProfile: () -> Unit,
    val openPost: () -> Unit
)