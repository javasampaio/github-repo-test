package com.challenge.datasource.response

import com.challenge.datasource.dto.RepoDto
import com.challenge.domain.model.GitRepoModel
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("items")
    val items: List<RepoDto>?
) {
    fun toGitRepo(): List<GitRepoModel>? {
        return items?.map { it.toModel() }
    }
}
