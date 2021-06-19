package com.challenge.datasource.client

import com.challenge.datasource.response.RepoResponse
import com.challenge.domain.model.GitPullModel
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.GET

interface GitClient {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepo(@Query("page") page: Int = 0): RepoResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPull(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<GitPullModel>
}