package com.challenge.domain.di

import com.challenge.domain.usecases.GetPullUseCase
import com.challenge.domain.usecases.GetPullUseCaseImpl
import com.challenge.domain.usecases.GetRepoUseCase
import com.challenge.domain.usecases.GetRepoUseCaseImpl
import org.koin.dsl.module

object DomainDI {

    val module = module {
        single<GetRepoUseCase> {
            GetRepoUseCaseImpl(get())
        }

        single<GetPullUseCase> {
            GetPullUseCaseImpl(get())
        }
    }
}