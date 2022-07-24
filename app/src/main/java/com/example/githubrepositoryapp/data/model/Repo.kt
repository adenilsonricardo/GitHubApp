package com.example.githubrepositoryapp.data.model

import java.security.acl.Owner

data class Repo(
    val id: Long,
    val name: String,
    val owner: Owner,
    val stargazersCount:String,
    val language:String,
    val htmlURL:String,
    val description:String
)
