package com.example.whereto.ui.main.trip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whereto.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_trip.*

class TripFragment : Fragment(R.layout.fragment_trip) {

    private val args: TripFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trip_web_view.settings.javaScriptEnabled = true

        activity?.bottom_nav?.visibility = View.GONE

        when(args.trip) {

            "0" -> findNavController().popBackStack()

            "Austria" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/austria.html")

            "India" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/india.html")

            "Indonesia" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/indonesia.html")

            "Italy" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/italy.html")

            "Slovakia" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/slovakia.html")

            "Thailand" -> trip_web_view.loadUrl("https://wheretoweb.000webhostapp.com/pages/thailand.html")
        }


        findNavController().addOnDestinationChangedListener { controller, destination, arguments ->

            if (destination.id != R.id.tripFragment)
                activity?.bottom_nav?.visibility = View.VISIBLE
        }
    }
}