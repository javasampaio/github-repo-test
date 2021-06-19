package com.challenge.datasource.dto

import com.challenge.domain.model.GitPullModel

data class PullDto(
    val user: UserDto,
    val title: String,
    val body: String,
    val created_at: String
) {
    fun toModel(): GitPullModel {
        return GitPullModel(
            namePr = user.login,
            titlePr = title,
            datePr = created_at,
            bodyPr = body
        )
    }
}
