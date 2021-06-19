package com.challenge.datasource.data

import com.challenge.datasource.client.GitClient
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.model.GitRepoModel


class GitRemoteDatasource(private val gitClient: GitClient) : GitDatasource {
    override suspend fun getRepo(page: Int): List<GitRepoModel> {
        return gitClient.getRepo(page).toGitRepo() ?: emptyList()
    }

    override suspend fun getPulls(author: String, repo: String): List<GitPullModel> {
        return gitClient.getPull(author, repo).map { it.toModel() }
    }
}