package com.challenge.domain.usecases

import com.challenge.common.state.State
import com.challenge.domain.model.GitRepoModel
import com.challenge.domain.repository.GitRepository

class GetRepoUseCaseImpl(private val gitRepository: GitRepository) : GetRepoUseCase {
    override suspend fun invoke(page: Int): State<List<GitRepoModel>> {
        return gitRepository.getRepo(page)
    }
}