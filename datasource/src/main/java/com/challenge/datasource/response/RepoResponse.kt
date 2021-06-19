package com.challenge.datasource.response

import com.challenge.datasource.dto.ItemsDto
import com.challenge.domain.model.GitRepoModel
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("items")
    val items: List<ItemsDto>?
) {
    fun toGitRepo(): List<GitRepoModel>? {
        return items?.map { it.toModel() }
    }
}
