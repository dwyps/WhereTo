package com.example.whereto.ui.main.profile.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whereto.ui.main.profile.ProfileTravelledFragment
import com.example.whereto.ui.main.profile.ProfileWishListFragment

class ProfileTabAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {

            0 -> ProfileTravelledFragment()

            1 -> ProfileWishListFragment()

            else -> ProfileTravelledFragment()
        }
    }
}