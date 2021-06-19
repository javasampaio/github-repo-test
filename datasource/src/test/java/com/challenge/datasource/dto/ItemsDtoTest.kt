package com.challenge.datasource.dto

import com.challenge.domain.model.GitRepoModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemsDtoTest {

    @Test
    fun toModelShouldParseToModel() {
        Assert.assertEquals(itemDto.toModel(), itemModel)
    }

    val itemDto = ItemsDto(
        name = "git repo",
        owner = getOwner(),
        description = "git repo description",
        stargazers_count = 1122,
        forks = 100
    )

    val itemModel = GitRepoModel(
        nameRepo = "git repo",
        nameOwner = getOwner().login,
        photoOwner = getOwner().avatar_url,
        description = "git repo description",
        numberStars = 1122,
        forks = 100
    )

    private fun getOwner(): OwnerDto = OwnerDto(login = "user", 123, "https://avatar.test.com")
}