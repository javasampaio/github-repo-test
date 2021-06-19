package com.challenge.datasource.di

import com.challenge.datasource.data.GitDatasource
import com.challenge.datasource.data.GitRemoteDatasource
import com.challenge.datasource.repository.GitRepositoryImpl
import com.challenge.domain.repository.GitRepository
import org.koin.dsl.module

object DatasourceDI {

    val module = module {

        factory<GitDatasource> {
            GitRemoteDatasource(get())
        }

        factory<GitRepository> {
            GitRepositoryImpl(get())
        }
    }
}