package com.example.whereto.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.whereto.data.model.Trip
import com.google.firebase.database.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class Repository {

    private val allTrips = mutableListOf<Trip>()

    private var databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("trips")

    private val homeListSize = 5


    init {

        getData()
    }

    companion object {

        @Volatile private var instance: Repository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Repository().also { instance = it }
            }
    }


    fun getAllTrips(): MutableList<Trip> {

        if (allTrips.isNotEmpty())
            allTrips.random()

        return allTrips
    }

    fun getLowestPriceTrips(): MutableList<Trip> {

        allTrips.sortBy { it.price }

        return allTrips
    }

    fun getHighestPriceTrips(): MutableList<Trip> {

        allTrips.sortByDescending { it.price }

        return allTrips

    }

    fun getBestSellingTrips(): MutableList<Trip> {

        allTrips.sortByDescending { it.timesSold }

        return allTrips
    }

    fun getNewestTrips(): MutableList<Trip> {

        allTrips.sortByDescending { it.date }

        return allTrips
    }

    fun getSearchedTrip(searchTerm: String): MutableList<Trip> {

        val searchList = mutableListOf<Trip>()

        allTrips.forEach {
            if (it.name.contains(searchTerm, true))
                searchList.add(it)
        }

        return searchList
    }

    fun getWishList(): MutableList<Trip> {

        val wishList = mutableListOf<Trip>()

        allTrips.forEach {
            if (it.wishList)
                wishList.add(it)
        }

        return wishList
    }

    fun getTraveledList(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        allTrips.forEach {
            if (it.travelled)
                travelledList.add(it)
        }

        return travelledList
    }

    fun getRecommendedTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()


        allTrips.sortByDescending { it.timesSold }

        allTrips.forEach {

            Timber.e("one trip")
            if (travelledList.size < homeListSize) {
                if (it.price.toInt() > 700) {

                    travelledList.add(it)
                }
            }
        }

        return travelledList
    }

    fun getPopularTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        getBestSellingTrips()

        allTrips.forEach {
            if (travelledList.size < homeListSize)
                travelledList.add(it)

        }

        return travelledList
    }

    fun getCheapestTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        getLowestPriceTrips()

        allTrips.forEach {
            if (travelledList.size < homeListSize)
                travelledList.add(it)
        }

        return travelledList
    }

    fun getFlashDealTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        getLowestPriceTrips()

        allTrips.forEach {
            if (it.flashDeal)
                travelledList.add(it)
        }

        return travelledList
    }

    fun getRomanticDealTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        getLowestPriceTrips()

        allTrips.forEach {
            if (it.romanticDeal)
                travelledList.add(it)
        }

        return travelledList
    }

    fun getAdventureDealTrips(): MutableList<Trip> {

        val travelledList = mutableListOf<Trip>()

        getLowestPriceTrips()

        allTrips.forEach {
            if (it.adventureDeal)
                travelledList.add(it)
        }

        return travelledList
    }

    fun setWishList(trip: String, state: Boolean) {
        databaseReference.child(trip).child("wish_list").setValue(state)
    }

    fun setTravelled(trip: String, state: Boolean) {
        databaseReference.child(trip).child("travelled").setValue(state)
    }


    private fun getData() {

        try {

            databaseReference.addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) {

                    Timber.e(error.message)
                }

                @SuppressLint("SimpleDateFormat")
                override fun onDataChange(snapshot: DataSnapshot) {

                    allTrips.clear()

                    for (trip in snapshot.children) {

                        val format= SimpleDateFormat("dd/MM/yyyy")
                        val date: Date? = format.parse(trip.child("date").value.toString())

                        allTrips.add(
                            Trip(
                                date!!,
                                trip.child("description").value.toString(),
                                trip.child("name").value.toString(),
                                trip.child("price").value as Long,
                                trip.child("thumbnail").value.toString(),
                                trip.child("times_sold").value as Long,
                                trip.child("travelled").value as Boolean,
                                trip.child("wish_list").value as Boolean,
                                trip.child("flash_deal").value as Boolean,
                                trip.child("romantic_deal").value as Boolean,
                                trip.child("adventure_deal").value as Boolean
                            )
                        )
                    }
                }
            })

        } catch (e: Exception) {

            e.printStackTrace()
        }

    }

}