package com.example.whereto.ui.main.trips

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whereto.data.model.Trip
import com.example.whereto.data.repository.Repository

class TripsViewModel (
    private val repository: Repository
) : ViewModel() {

    private val mTrips: MutableLiveData<MutableList<Trip>> = MutableLiveData()
    val trips: LiveData<MutableList<Trip>> = mTrips


    fun getAllTrips() {

        mTrips.value = repository.getAllTrips()
    }

    fun getSearchedTrip(searchTerm: String) {

        mTrips.value = repository.getSearchedTrip(searchTerm)
    }

    fun getNewestTrips() {

        mTrips.value = repository.getNewestTrips()
    }

    fun getLowestPriceTrips() {

        mTrips.value = repository.getLowestPriceTrips()

    }

    fun getHighestPriceTrips() {

        mTrips.value = repository.getHighestPriceTrips()
    }

    fun getBestSellingTrips() {

        mTrips.value = repository.getBestSellingTrips()
    }

    fun setWishList(trip: String, state: Boolean) {
        repository.setWishList(trip, state)
    }

    fun setTravelled(trip: String, state: Boolean) {
        repository.setTravelled(trip, state)
    }

}
