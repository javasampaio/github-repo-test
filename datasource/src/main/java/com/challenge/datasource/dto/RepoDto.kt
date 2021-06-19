package com.challenge.datasource.dto

import com.challenge.domain.model.GitRepoModel

data class RepoDto(
    val name: String,
    val owner: OwnerDto,
    val description: String,
    val stargazers_count: Int,
    val forks: Int
) {
    fun toModel(): GitRepoModel {
        return GitRepoModel(
            name = name,
            nameOwner = owner.login,
            photoOwner = owner.avatar_url,
            description = description,
            numberStars = stargazers_count,
            forks = forks
        )
    }
}
