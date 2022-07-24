package com.example.githubrepositoryapp.data.repositories

import android.os.RemoteException
import com.bumptech.glide.load.HttpException
import com.example.githubrepositoryapp.data.services.GitHubServices
import kotlinx.coroutines.flow.flow

class RepoRepositoryImpl(private val service: GitHubServices): RepoRepository {
    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.listRepositories(user)
            emit(repoList)
        }catch (ex: HttpException){
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento.")
        }
    }
}