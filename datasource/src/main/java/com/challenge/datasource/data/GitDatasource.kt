package com.challenge.datasource.data

import com.challenge.domain.model.GitPullModel
import com.challenge.domain.model.GitRepoModel

interface GitDatasource {

    suspend fun getRepo(page: Int): List<GitRepoModel>
    suspend fun getPulls(author: String, repo: String): List<GitPullModel>
}