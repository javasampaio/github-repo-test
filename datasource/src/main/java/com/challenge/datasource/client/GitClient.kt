package com.challenge.datasource.client

import com.challenge.datasource.dto.PullDto
import com.challenge.datasource.response.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitClient {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepo(@Query("page") page: Int = 0): RepoResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPull(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<PullDto>
}