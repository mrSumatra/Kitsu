package lsvapp.kitsu.domain.interactor

import lsvapp.kitsu.data.repository.GroupeRepository
import lsvapp.kitsu.domain.entity.Groupe
import lsvapp.kitsu.domain.entity.utils.DtoConverter

class GroupeInteractor(
    private val repository: GroupeRepository,
    private val dtoConverter: DtoConverter
) {
    suspend fun getGroups(page: Int? = null, size: Int? = null): List<Groupe> {
        return repository.getGroups(page, size).data.map {
            dtoConverter.dataToGroupe(it)
        }
    }

    suspend fun getGroupsById(id: Long): Groupe =
        dtoConverter.dataToGroupe(repository.getGroupsById(id).data)

}