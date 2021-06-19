package com.challenge.domain.usecases

import com.challenge.common.state.State
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.repository.GitRepository

class GetPullUseCaseImpl(private val gitRepository: GitRepository) : GetPullUseCase {
    override suspend fun invoke(author: String, repo: String): State<List<GitPullModel>> {
        return gitRepository.getPulls(author, repo)
    }
}