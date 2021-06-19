package com.challenge.datasource.repository

import com.challenge.common.error.ErrorHandler
import com.challenge.common.state.ErrorState
import com.challenge.common.state.Loaded
import com.challenge.common.state.State
import com.challenge.datasource.data.GitDatasource
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.model.GitRepoModel
import com.challenge.domain.repository.GitRepository

class GitRepositoryImpl(private val gitDatasource: GitDatasource) : GitRepository {
    override suspend fun getRepo(page: Int): State<List<GitRepoModel>> {
        return try {
            Loaded(gitDatasource.getRepo(page))
        } catch (t: Throwable) {
            ErrorState(ErrorHandler.mapError(t))
        }
    }

    override suspend fun getPulls(author: String, repo: String): State<List<GitPullModel>> {
        return try {
            Loaded(gitDatasource.getPulls(author, repo))
        } catch (t: Throwable) {
            ErrorState(ErrorHandler.mapError(t))
        }
    }
}