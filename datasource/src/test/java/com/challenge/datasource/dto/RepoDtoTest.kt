package com.challenge.datasource.dto

import com.challenge.domain.model.GitRepoModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepoDtoTest {

    @Test
    fun toModelShouldParseToModel() {
        Assert.assertEquals(repoDto.toModel(), repoModel)
    }

    val repoDto = RepoDto(
        name = "git repo",
        owner = getOwner(),
        description = "git repo description",
        stargazers_count = 1122,
        forks = 100
    )

    val repoModel = GitRepoModel(
        nameRepo = "git repo",
        nameOwner = getOwner().login,
        photoOwner = getOwner().avatar_url,
        description = "git repo description",
        numberStars = 1122,
        forks = 100
    )

    private fun getOwner(): OwnerDto = OwnerDto(login = "user", 123, "https://avatar.test.com")
}