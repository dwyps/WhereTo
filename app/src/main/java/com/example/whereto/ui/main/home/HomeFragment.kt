package com.example.whereto.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

import com.example.whereto.R
import com.example.whereto.data.model.Deal
import com.example.whereto.data.model.News
import com.example.whereto.ui.main.MainActivity
import com.example.whereto.ui.main.home.adapter.HomeNewsRecyclerAdapter
import com.example.whereto.ui.main.home.adapter.HomeRecyclerAdapter
import com.example.whereto.ui.main.home.adapter.HomeTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment), HomeRecyclerAdapter.OnItemListener{

    private lateinit var viewPagerAdapter: HomeTabAdapter
    private lateinit var recyclerViewAdapter: HomeRecyclerAdapter
    private lateinit var newsRecyclerAdapter: HomeNewsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        initRecyclerView()

        viewPagerAdapter = HomeTabAdapter(this, 3)

        home_view_pager.adapter = viewPagerAdapter
        home_view_pager.isUserInputEnabled = false
        home_view_pager.requestDisallowInterceptTouchEvent(true)

        TabLayoutMediator(home_tab_layout, home_view_pager) { tab, position ->

            when(position) {

                0 -> {
                    tab.text = "Recommended"
                }

                1 -> {
                    tab.text = "Popular"
                }

                2 -> {
                    tab.text = "Cheapest"
                }
            }

        }.attach()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).ensureBottomNavigation()
    }

    //TODO CHANGE TO REPOSITORY
    private fun initRecyclerView() {

        val dealsList = arrayListOf<Deal>()

        dealsList.add(
            Deal(
                "Flash Deals",
                resources.getDrawable(R.drawable.ic_flash_deal)
            )
        )
        dealsList.add(
            Deal(
                "Top Deals",
                resources.getDrawable(R.drawable.ic_top_deal)
            )
        )
        dealsList.add(
            Deal(
                "Romantic Deals",
                resources.getDrawable(R.drawable.ic_romantic_deal)
            )
        )
        dealsList.add(
            Deal(
                "Adventure Deals",
                resources.getDrawable(R.drawable.ic_adventure_deal)
            )
        )
        dealsList.add(
            Deal(
                "Wish List",
                resources.getDrawable(R.drawable.ic_wish_list)
            )
        )


        recyclerViewAdapter = HomeRecyclerAdapter(this)

        recyclerViewAdapter.submitList(dealsList)

        home_recycler_view_deals.adapter = recyclerViewAdapter


        val newsList = arrayListOf<News>()

        newsList.add(
            News(
                "Covid-19 and Travelling",
                resources.getDrawable(R.drawable.item_news_covid),
                "The corona virus outbreak is a serious threat to public health." +
                        " Lockdowns and other coordinated restrictive measures are necessary to save lives. However," +
                        " these measures may also severely slow down our economies and can delay the deliveries of critical goods and services."
            )
        )

        newsList.add(
            News(
                "European aviation",
                resources.getDrawable(R.drawable.item_news_european_aviation),
                "Europe’s aviation sector is committed to contributing to the recovery of European economies in line with the Green Deal objectives." +
                        " The sector therefore calls on policymakers to include smart measures to support Europe’s civil aviation sector during its recovery." +
                        " It requires ensuring that aviation climate action is eligible for funding under the mechanisms."
            )
        )

        newsList.add(
            News(
                "Business travel training",
                resources.getDrawable(R.drawable.item_news_learn),
                "Business travel courses will be offered by C&M Travel Recruitment in a new initiative." +
                        " A joint venture with specialist training firm Travilearn will see job seekers registered with C&M become eligible for discounts on " +
                        " courses and the opportunity to earn an industry specific diploma, certified by the Business Travel Association."
            )
        )


        newsRecyclerAdapter = HomeNewsRecyclerAdapter()

        newsRecyclerAdapter.submitList(newsList)

        home_recycler_view_news.adapter = newsRecyclerAdapter

        home_recycler_view_news.isNestedScrollingEnabled = false

    }

    private fun setListeners() {

        home_btn_search.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTripsFragment())
        }
    }

    override fun onItemClick(position: Int, deal: Deal) {

        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTripsFragment())
    }

}
