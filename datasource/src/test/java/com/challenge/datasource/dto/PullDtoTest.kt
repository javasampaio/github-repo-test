package com.challenge.datasource.dto

import com.challenge.domain.model.GitPullModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullDtoTest {

    @Test
    fun toModelShouldParseToModel() {
        Assert.assertEquals(pullDto.toModel(), pullModel)
    }

    private val pullDto = PullDto(
        title = "git pull",
        user = getUser(),
        body = "git body",
        created_at = "2021-05-20T20:44:43Z"

    )

    private val pullModel = GitPullModel(
        titlePr = "git pull",
        namePr = getUser().login,
        userImage = getUser().avatar_url ?: "",
        bodyPr = "git body",
        datePr = "2021-05-20T20:44:43Z"
    )

    private fun getUser(): UserDto = UserDto(login = "user", avatar_url = "https://avatar.test.com")
}