package com.example.whereto.ui.main.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.whereto.R
import com.example.whereto.data.model.Trip
import com.example.whereto.ui.main.profile.adapter.RecyclerAdapter
import com.example.whereto.util.InjectorUtils
import kotlinx.android.synthetic.main.profile_tab_recycler_view_fragment.*

class ProfileWishListFragment : Fragment(R.layout.profile_tab_recycler_view_fragment), RecyclerAdapter.OnItemListener {

    private lateinit var profileRecyclerAdapter: RecyclerAdapter

    private val profileViewModel: ProfileViewModel by viewModels {
        InjectorUtils.provideViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        profileViewModel.getWishList()

        profileViewModel.trips.observe(viewLifecycleOwner, Observer {

            profileRecyclerAdapter.submitList(it)
            profileRecyclerAdapter.notifyDataSetChanged()
        })

        profile_refresh_layout.setOnRefreshListener {
            profileRecyclerAdapter.notifyDataSetChanged()
            profile_refresh_layout.isRefreshing = false
        }

    }

    private fun initRecyclerView() {

        profile_recycler_view.apply {

            profileRecyclerAdapter = RecyclerAdapter(this@ProfileWishListFragment)
            profileRecyclerAdapter.submitList(listOf())
            adapter = profileRecyclerAdapter


        }
    }

    override fun onItemClick(position: Int, trip: Trip) {
        Toast.makeText(context, "Was clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(position: Int, trip: Trip) {

        val options = arrayOf("Remove from wish list")

        AlertDialog.Builder(context)
            .setTitle("Options")
            .setItems(options) {dialog, which ->

                when(which) {

                    0 -> {
                        profileViewModel.setWishList(trip.name, false)
                        profileRecyclerAdapter.notifyDataSetChanged()
                        dialog.cancel()
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}