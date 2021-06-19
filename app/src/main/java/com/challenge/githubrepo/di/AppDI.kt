package com.challenge.githubrepo.di

import com.challenge.githubrepo.fragments.pull.GitPullViewModel
import com.challenge.githubrepo.fragments.repo.GitRepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppDI {

    val module = module {

        viewModel {
            GitRepoViewModel(
                getRepoUseCase = get()
            )
        }

        viewModel { (author: String, repoName: String) ->
            GitPullViewModel(
                author = author,
                repoName = repoName,
                getPullUseCase = get()
            )
        }
    }
}