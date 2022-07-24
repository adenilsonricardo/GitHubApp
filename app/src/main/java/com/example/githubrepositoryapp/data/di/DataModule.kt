package com.example.githubrepositoryapp.data.di

import android.util.Log
import com.example.githubrepositoryapp.data.repositories.RepoRepository
import com.example.githubrepositoryapp.data.repositories.RepoRepositoryImpl
import com.example.githubrepositoryapp.data.services.GitHubServices
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    private const val OK_HTTP = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module{
            single{
                val interceptor = HttpLoggingInterceptor{
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single{
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single{
                createService<GitHubServices>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module{
            single<RepoRepository> {RepoRepositoryImpl(get())}
        }
    }

    private inline fun <reified T> createService(): T {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}