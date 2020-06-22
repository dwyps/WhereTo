package com.example.whereto.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whereto.R
import com.example.whereto.model.Trip

@BindingAdapter("bindThumbnail")
fun bindThumbnail(imgView: ImageView, trip: Trip) {

    trip.let {

        Glide.with(imgView.context)
            .asDrawable()
            .load(trip.thumbnail)
            .apply(RequestOptions()
                .placeholder(R.color.white)
                .error(R.drawable.ic_logo))
            .into(imgView)
    }
}