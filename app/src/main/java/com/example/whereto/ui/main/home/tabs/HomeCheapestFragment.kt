package com.example.whereto.ui.main.home.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.whereto.R
import com.example.whereto.data.model.Trip
import com.example.whereto.ui.main.home.HomeViewModel
import com.example.whereto.ui.main.home.adapter.HomeTripsRecyclerAdapter
import com.example.whereto.util.InjectorUtils
import kotlinx.android.synthetic.main.home_tab_recycler_view_fragment.*

class HomeCheapestFragment : Fragment(R.layout.home_tab_recycler_view_fragment), HomeTripsRecyclerAdapter.OnItemListener  {

    private lateinit var homeRecyclerAdapter: HomeTripsRecyclerAdapter

    private val homeViewModel: HomeViewModel by viewModels {
        InjectorUtils.provideViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        homeViewModel.getCheapestTrips()
        homeViewModel.trips.observe(viewLifecycleOwner, Observer {

            homeRecyclerAdapter.submitList(it)
            homeRecyclerAdapter.notifyDataSetChanged()

        })
    }

    override fun onResume() {
        super.onResume()

        homeRecyclerAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView() {

        home_tab_recycler_view.apply {

            homeRecyclerAdapter = HomeTripsRecyclerAdapter(this@HomeCheapestFragment)

            adapter = homeRecyclerAdapter
        }
    }

    override fun onItemClick(position: Int, trip: Trip) {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
    }
}