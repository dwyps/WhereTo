package com.example.whereto.ui.main.trips

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.whereto.R
import com.example.whereto.data.model.Trip
import com.example.whereto.ui.main.MainActivity
import com.example.whereto.ui.main.profile.adapter.RecyclerAdapter
import com.example.whereto.util.InjectorUtils
import kotlinx.android.synthetic.main.trips_fragment.*

class TripsFragment : Fragment(R.layout.trips_fragment), RecyclerAdapter.OnItemListener{

    private lateinit var tripsRecyclerAdapter: RecyclerAdapter

    private val tripsViewModel: TripsViewModel by viewModels {
        InjectorUtils.provideViewModelFactory()
    }

    //TODO FONT IN SPINNER, FILTERS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initSortSpinner()
        initRecyclerView()


        tripsViewModel.getAllTrips()

        tripsViewModel.trips.observe(viewLifecycleOwner, Observer {

            tripsRecyclerAdapter.submitList(it)
            tripsRecyclerAdapter.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).ensureBottomNavigation()
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

        val filters = arrayOf("Best Selling", "Newest", "Lowest Price", "Highest Price")
        val spinnerAdapter = ArrayAdapter (requireContext(), android.R.layout.simple_spinner_item, filters)

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
                    0 -> {

                        tripsViewModel.getBestSellingTrips()
                    }

                    1 -> {

                        tripsViewModel.getNewestTrips()
                    }

                    2 -> {

                        tripsViewModel.getLowestPriceTrips()
                    }

                    3 -> {
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

        Toast.makeText(context, "Was clicked!", Toast.LENGTH_SHORT).show()
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
