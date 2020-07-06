package com.example.whereto.ui.main.trips

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whereto.R
import com.example.whereto.data.model.Trip
import com.example.whereto.ui.main.MainActivity
import com.example.whereto.ui.main.home.HomeFragmentDirections
import com.example.whereto.ui.main.profile.adapter.RecyclerAdapter
import com.example.whereto.util.InjectorUtils
import kotlinx.android.synthetic.main.trips_fragment.*

class TripsFragment : Fragment(R.layout.trips_fragment), RecyclerAdapter.OnItemListener{

    private lateinit var tripsRecyclerAdapter: RecyclerAdapter

    private lateinit var spinnerAdapter: ArrayAdapter<String>

    private val args: TripsFragmentArgs by navArgs()

    private val tripsViewModel: TripsViewModel by viewModels {
        InjectorUtils.provideViewModelFactory()
    }

    private val handler = Handler()
    private lateinit var runnable: Runnable

    //TODO FONT IN SPINNER, FILTERS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListeners()
        initSortSpinner()
        initRecyclerView()
        checkDeals()

        tripsViewModel.trips.observe(viewLifecycleOwner, Observer {

            tripsRecyclerAdapter.submitList(it)
            tripsRecyclerAdapter.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).ensureBottomNavigation()

        runnable = Runnable {

            tripsRecyclerAdapter.notifyDataSetChanged()

            if (tripsRecyclerAdapter.currentList.isEmpty())
                handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }



    private fun checkDeals() {

        when(args.deal) {

            0 -> {
                tripsViewModel.getAllTrips()
            }

            1 -> {
                tripsViewModel.getFlashDealTrips()
            }

            2 -> {
                trips_sort_by_spinner.setSelection(1)
            }

            3 -> {
                tripsViewModel.getRomanticDealTrips()
            }

            4 -> {
                tripsViewModel.getAdventureDealTrips()
            }
        }
    }

    private fun initListeners() {

        trips_refresh_layout.setOnRefreshListener {
            tripsRecyclerAdapter.notifyDataSetChanged()
            trips_refresh_layout.isRefreshing = false
        }

        trips_btn_search.setOnClickListener {

            tripsViewModel.getSearchedTrip(trips_edit_text_search.text.toString())
        }
    }

    private fun initSortSpinner() {

        val filters = arrayOf("All","Best Selling", "Newest", "Lowest Price", "Highest Price")
        spinnerAdapter = ArrayAdapter (requireContext(), android.R.layout.simple_spinner_item, filters)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        trips_sort_by_spinner.adapter = spinnerAdapter
        trips_sort_by_spinner.dropDownVerticalOffset = 10

        trips_sort_by_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                tripsViewModel.getAllTrips()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position) {

                    0 -> { }

                    1 -> {

                        tripsViewModel.getBestSellingTrips()
                    }

                    2 -> {

                        tripsViewModel.getNewestTrips()
                    }

                    3 -> {

                        tripsViewModel.getLowestPriceTrips()
                    }

                    4 -> {

                        tripsViewModel.getHighestPriceTrips()
                    }
                 }
            }

        }
    }

    private fun initRecyclerView() {

        trips_recycler_view.apply {

            tripsRecyclerAdapter = RecyclerAdapter(this@TripsFragment)
            tripsRecyclerAdapter.submitList(listOf())

            adapter= tripsRecyclerAdapter
        }
    }

    override fun onItemClick(position: Int, trip: Trip) {

        findNavController().navigate(TripsFragmentDirections.actionTripsFragmentToTripFragment(trip.name))
    }

    override fun onItemLongClick(position: Int, trip: Trip) {

        val options = arrayOf("Add to Wish List", "Was there")

        AlertDialog.Builder(context)
            .setItems(options) {dialog, which ->

                when(which) {

                    0 -> {
                        tripsViewModel.setWishList(trip.name, true)
                    }

                    1 -> {

                        tripsViewModel.setTravelled(trip.name, true)
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
        Toast.makeText(context, "Was long clicked!", Toast.LENGTH_SHORT).show()
    }
}
