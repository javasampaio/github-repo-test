package com.challenge.domain.repository

import com.challenge.common.state.State
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.model.GitRepoModel

interface GitRepository {
    suspend fun getRepo(page: Int): State<List<GitRepoModel>>
    suspend fun getPulls(author: String, repo: String): State<List<GitPullModel>>
}