package com.example.whereto.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.whereto.R
import com.example.whereto.model.Trip
import com.example.whereto.ui.main.profile.adapter.ProfileRecyclerAdapter
import kotlinx.android.synthetic.main.recycler_view_fragment.*

class ProfileViewPagerFragment : Fragment(R.layout.recycler_view_fragment) {

    private lateinit var profileRecyclerAdapter: ProfileRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        val tripsList = mutableListOf<Trip>()

        tripsList.add(Trip("Thailand", "From Bangkok to Phi Phi Island", resources.getDrawable(R.drawable.thumb_thailand)))
        tripsList.add(Trip("Austria", "The Best of Austria", resources.getDrawable(R.drawable.thumb_austria)))
        tripsList.add(Trip("India", "Taj Mahal and Wildlife", resources.getDrawable(R.drawable.thumb_india)))
        tripsList.add(Trip("Indonesia", "Wanderlands Bali", resources.getDrawable(R.drawable.thumb_indonesia)))
        tripsList.add(Trip("Italy", "Taste of Italy", resources.getDrawable(R.drawable.thumb_italy)))
        tripsList.add(Trip("Slovakia", "Hiking in Slovakia", resources.getDrawable(R.drawable.thumb_slovakia)))

        profileRecyclerAdapter.submitList(tripsList)
    }


    private fun initRecyclerView() {

        profile_recycler_view.apply {

            profileRecyclerAdapter = ProfileRecyclerAdapter()

            adapter = profileRecyclerAdapter


        }
    }
}