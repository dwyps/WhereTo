package com.example.whereto.ui.main.home.tabs

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.whereto.R
import com.example.whereto.data.model.Trip
import com.example.whereto.ui.main.home.HomeFragment
import com.example.whereto.ui.main.home.HomeFragmentDirections
import com.example.whereto.ui.main.home.HomeViewModel
import com.example.whereto.ui.main.home.adapter.HomeTripsRecyclerAdapter
import com.example.whereto.util.InjectorUtils
import kotlinx.android.synthetic.main.home_tab_recycler_view_fragment.*

class HomeRecommendedFragment : Fragment(R.layout.home_tab_recycler_view_fragment), HomeTripsRecyclerAdapter.OnItemListener {

    private lateinit var homeRecyclerAdapter: HomeTripsRecyclerAdapter

    private val homeViewModel: HomeViewModel by viewModels {
        InjectorUtils.provideViewModelFactory()
    }

    private val handler = Handler()
    private lateinit var runnable: Runnable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        homeViewModel.trips.observe(viewLifecycleOwner, Observer {

            homeRecyclerAdapter.submitList(it)
            homeRecyclerAdapter.notifyDataSetChanged()

        })
    }

    override fun onResume() {
        super.onResume()

        runnable = Runnable {

            homeViewModel.getRecommendedTrips()
            homeRecyclerAdapter.notifyDataSetChanged()

            if (homeRecyclerAdapter.currentList.isEmpty())
                handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    private fun initRecyclerView() {

        home_tab_recycler_view.apply {

            homeRecyclerAdapter = HomeTripsRecyclerAdapter(this@HomeRecommendedFragment)

            adapter = homeRecyclerAdapter
        }
    }

    override fun onItemClick(position: Int, trip: Trip) {

        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTripFragment(trip.name))
    }
}