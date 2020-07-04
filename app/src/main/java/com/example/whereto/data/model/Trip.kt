package com.example.whereto.data.model

import java.util.*

data class Trip(
    var date: Date,
    var description: String,
    var name: String,
    var price: Long,
    var thumbnail: String,
    var timesSold: Long,
    var travelled: Boolean,
    var wishList: Boolean
)