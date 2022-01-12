package lsvapp.kitsu.presentation.group.adapter

import lsvapp.kitsu.domain.entity.Groupe

data class GroupeTabAdapterItem(
    val groupe: Groupe,
    val action: () -> Unit
)