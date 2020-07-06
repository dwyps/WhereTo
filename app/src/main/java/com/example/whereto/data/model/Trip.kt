package com.example.whereto.data.model

import java.util.*

data class Trip(
    val date: Date,
    val description: String,
    val name: String,
    val price: Long,
    val thumbnail: String,
    val timesSold: Long,
    var travelled: Boolean,
    var wishList: Boolean,
    val flashDeal: Boolean,
    val romanticDeal: Boolean,
    val adventureDeal: Boolean
)