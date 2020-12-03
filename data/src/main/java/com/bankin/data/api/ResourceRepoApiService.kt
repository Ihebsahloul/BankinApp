
package com.bankin.data.model

import com.bankin.data.response.ResourceResponse
import com.mvvmclean.trendingrepos.data.models.response.search.ResourceSearchResponse
import retrofit2.http.GET


interface ResourceRepoApiService {
    @GET("categories.json")
    suspend fun resourceRepoSearchRepositories(): ResourceSearchResponse
}