package com.example.whereto.util

import com.example.whereto.data.repository.Repository

object InjectorUtils {

    private fun getRepository(): Repository {
        return Repository.getInstance()
    }

    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(getRepository())
    }

}