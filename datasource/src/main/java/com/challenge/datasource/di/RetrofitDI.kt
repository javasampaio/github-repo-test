package com.challenge.datasource.di

import com.challenge.datasource.BuildConfig
import com.challenge.datasource.client.GitClient
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDI {
    private const val BASE_URL_NAME = "BASE_URL"

    val module = module {
        factory<GitClient> {
            get<Retrofit>().create(GitClient::class.java)
        }

        single(named(BASE_URL_NAME)) { BuildConfig.BASE_URL }

        factory {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        factory {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        factory<Retrofit.Builder> {
            Retrofit.Builder()
                .client(get())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().create()
                    )
                )
        }

        factory<Retrofit> {
            get<Retrofit.Builder>()
                .baseUrl(get<String>(named(BASE_URL_NAME)))
                .build()

        }
    }
}