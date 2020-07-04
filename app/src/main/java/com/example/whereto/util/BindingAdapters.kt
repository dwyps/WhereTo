package com.example.whereto.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.whereto.R
import com.example.whereto.data.model.Deal
import com.example.whereto.data.model.News
import com.example.whereto.data.model.Trip

@BindingAdapter("bindThumbnailTrip")
fun bindThumbnail(imgView: ImageView, trip: Trip) {

    trip.let {

        Glide.with(imgView.context)
            .load(trip.thumbnail)
            .apply(RequestOptions()
                .placeholder(R.color.white)
                .error(R.drawable.ic_logo))
            .into(imgView)
    }
}

@BindingAdapter("bindThumbnailDeal")
fun bindThumbnail(imgView: ImageView, deal: Deal) {

    deal.let {

        Glide.with(imgView.context)
            .asDrawable()
            .load(deal.thumbnail)
            .apply(RequestOptions()
                .placeholder(R.color.white)
                .error(R.drawable.ic_logo))
            .into(imgView)
    }
}

@BindingAdapter("bindThumbnailNews")
fun bindThumbnail(imgView: ImageView, news: News) {

    news.let {

        Glide.with(imgView.context)
            .asDrawable()
            .load(news.thumbnail)
            .apply(RequestOptions()
                .fitCenter()
                .transform(RoundedCorners(50))
                .placeholder(R.color.white)
                .error(R.drawable.ic_logo))
            .into(imgView)
    }
}