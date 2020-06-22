package com.example.whereto.ui.main.profile.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whereto.ui.main.profile.ProfileViewPagerFragment

class ProfileTabAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return ProfileViewPagerFragment()
    }
}