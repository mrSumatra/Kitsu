package lsvapp.kitsu.presentation.utils.widget.reaction.adapter

import lsvapp.kitsu.domain.entity.AnimeReaction

data class ReactionAdapterItem(
    val reaction: AnimeReaction,
    val action: () -> Unit
)