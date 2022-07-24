package com.example.githubrepositoryapp.data.repositories

import com.example.githubrepositoryapp.data.model.Repo

interface RepoRepository {
    suspend fun listRepositories(user: String): List<Repo>
}