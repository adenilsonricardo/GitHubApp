package com.example.githubrepositoryapp.domain

import com.example.githubrepositoryapp.data.model.Repo
import com.example.githubrepositoryapp.data.repositories.RepoRepository
import java.util.concurrent.Flow

class ListUserRepositoriesUserCase(
    private val repository: RepoRepository
    ): UseCase<String, List<Repo>> {

    override suspend fun execute(param: String): Flow<List<Repo>>{
        return repository.listRepositories(param)
    }
}