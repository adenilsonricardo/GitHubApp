package com.example.githubrepositoryapp.data.services

import com.example.githubrepositoryapp.data.model.Repo
import java.util.concurrent.Flow

interface GitHubServices {
    @GET("users/{user}/repos")
    suspend fun listRepositories (user: String): Flow<List<Repo>>
}