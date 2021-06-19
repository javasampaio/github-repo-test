package com.challenge.domain.model

data class GitRepoModel(
    val name: String,
    val description: String,
    val nameOwner: String,
    val imageOwner: String,
    val numberStars: Int,
    val forks: Int
)
