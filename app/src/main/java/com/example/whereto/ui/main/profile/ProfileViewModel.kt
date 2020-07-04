package com.example.whereto.ui.main.profile

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whereto.data.model.Trip
import com.example.whereto.data.repository.Repository

class ProfileViewModel (
    private val repository: Repository
    ) : ViewModel() {

    private val mTrips: MutableLiveData<MutableList<Trip>> = MutableLiveData()
    val trips: LiveData<MutableList<Trip>> = mTrips

    fun getWishList() {

        mTrips.value = repository.getWishList()
    }

    fun getTraveledList() {

        mTrips.value = repository.getTraveledList()
    }

    fun setWishList(trip: String, state: Boolean) {

        repository.setWishList(trip, state)
    }

    fun setTravelled(trip: String, state: Boolean) {

        repository.setTravelled(trip, state)
    }
}
