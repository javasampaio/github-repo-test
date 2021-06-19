package com.challenge.domain.usecases

import com.challenge.common.state.State
import com.challenge.domain.model.GitPullModel


interface GetPullUseCase {
    suspend operator fun invoke(author: String, repo: String): State<List<GitPullModel>>
}