package lsvapp.kitsu.data.repository

import lsvapp.kitsu.data.api.GroupeApi

class GroupeRepository(
    private val groupeApi: GroupeApi
) {
    suspend fun getGroups(page: Int? = null, size: Int? = null) = groupeApi.getGroups(page, size)

    suspend fun getGroupsById(id: Long) = groupeApi.getGroupsById(id)
}