
package com.bankin.data.model

import com.bankin.data.response.ResourceResponse
import retrofit2.http.GET


interface ResourceRepoApiService {
    @GET("categories.json")
    suspend fun resourceRepoSearchRepositories(): MutableList<ResourceResponse>
}