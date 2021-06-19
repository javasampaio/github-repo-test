package com.challenge.domain.usecases

import com.challenge.common.state.State
import com.challenge.domain.model.GitRepoModel

interface GetRepoUseCase {
    suspend operator fun invoke(page: Int): State<List<GitRepoModel>>
}