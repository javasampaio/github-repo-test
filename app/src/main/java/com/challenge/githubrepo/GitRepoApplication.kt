package com.challenge.githubrepo

import android.app.Application
import com.challenge.datasource.di.DatasourceDI
import com.challenge.datasource.di.RetrofitDI
import com.challenge.domain.di.DomainDI
import com.challenge.githubrepo.di.AppDI
import org.koin.core.context.startKoin

class GitRepoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(modules = getKoinModules())
        }
    }

    private fun getKoinModules() = DatasourceDI.module +
            RetrofitDI.module +
            DomainDI.module +
            AppDI.module
}