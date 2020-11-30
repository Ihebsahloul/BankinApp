
package com.bankin.data.model

import retrofit2.http.GET


interface ResourceRepoApiService {
    @GET("repositories")
    suspend fun resourceRepoSearchRepositories(): MutableList<ResourceEntity>
}