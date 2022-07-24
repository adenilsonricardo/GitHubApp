package com.example.githubrepositoryapp.domain.di

import android.provider.ContactsContract
import com.example.githubrepositoryapp.data.di.DataModule
import com.example.githubrepositoryapp.domain.ListUserRepositoriesUserCase
import java.lang.reflect.Array.get

object DomainModule {

    fun load() {
        loadKoinModules()
    }
    private fun useCaseModule(): Module {
        return module{
            factory { ListUserRepositoriesUserCase(get()) }
        }

        DataModule.load()
        DomainModule.load()
    }

}