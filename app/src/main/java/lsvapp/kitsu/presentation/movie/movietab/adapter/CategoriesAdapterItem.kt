package lsvapp.kitsu.presentation.movie.movietab.adapter

import lsvapp.kitsu.domain.entity.dto.CategoryDto

data class CategoriesAdapterItem(
    val category: CategoryDto,
    val action: () -> Unit
)