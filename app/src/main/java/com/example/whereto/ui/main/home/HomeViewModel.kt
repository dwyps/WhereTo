package com.example.whereto.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whereto.data.model.Trip
import com.example.whereto.data.repository.Repository

class HomeViewModel (
    private val repository: Repository
) : ViewModel() {

    private val mTrips: MutableLiveData<MutableList<Trip>> = MutableLiveData()
    val trips: LiveData<MutableList<Trip>> = mTrips


    fun getRecommendedTrips() {

        mTrips.value = repository.getRecommendedTrips()
    }

    fun getPopularTrips() {

        mTrips.value = repository.getPopularTrips()
    }

    fun getCheapestTrips() {

        mTrips.value = repository.getCheapestTrips()
    }

}
