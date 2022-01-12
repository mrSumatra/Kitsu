package lsvapp.kitsu.data.api

import lsvapp.kitsu.domain.entity.dto.GroupeDto
import lsvapp.kitsu.domain.entity.dto.Page
import lsvapp.kitsu.domain.entity.dto.PageList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupeApi {

    @GET("groups")
    suspend fun getGroups(
        @Query("page[offset]") page: Int? = null,
        @Query("page[limit]") size: Int? = null,
    ): PageList<GroupeDto>

    @GET("groups/{id}")
    suspend fun getGroupsById(@Path("id") id: Long): Page<GroupeDto>

}