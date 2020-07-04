package com.example.whereto.ui.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whereto.ui.main.home.tabs.HomeCheapestFragment
import com.example.whereto.ui.main.home.tabs.HomePopularFragment
import com.example.whereto.ui.main.home.tabs.HomeRecommendedFragment

class HomeTabAdapter (fragment: Fragment, private val count: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {

            0 -> HomeRecommendedFragment()

            1 -> HomePopularFragment()

            2 -> HomeCheapestFragment()

            else -> HomeRecommendedFragment()
        }
    }
}