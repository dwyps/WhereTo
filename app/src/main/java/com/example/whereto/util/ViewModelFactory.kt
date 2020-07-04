package com.example.whereto.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whereto.data.repository.Repository
import com.example.whereto.ui.main.home.HomeViewModel
import com.example.whereto.ui.main.profile.ProfileViewModel
import com.example.whereto.ui.main.trips.TripsViewModel

class ViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(TripsViewModel::class.java) -> TripsViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            else -> throw IllegalArgumentException("Wrong dependency")
        }

    }
}