package com.challenge.domain.model

data class GitRepoModel(
    val nameRepo: String,
    val description: String,
    val nameOwner: String,
    val photoOwner: String,
    val numberStars: Int,
    val forks: Int
)
