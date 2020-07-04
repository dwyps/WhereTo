package com.example.whereto.ui.main

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.whereto.R
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : FragmentActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val galleryPermissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (!EasyPermissions.hasPermissions(this, *galleryPermissions)) {
            EasyPermissions.requestPermissions(
                this, "Access for storage",
                101, *galleryPermissions
            )
        }
    }

    override fun onStart() {
        super.onStart()

        setupNavigation()
    }

    private fun setupNavigation() {

        navController = findNavController(R.id.nav_host_main)
        bottom_nav.setupWithNavController(navController)
    }

    fun ensureBottomNavigation() {
        if(bottom_nav.translationY != 0f) {
            val layoutParams = bottom_nav.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = layoutParams.behavior as HideBottomViewOnScrollBehavior

            behavior.onNestedScroll(activity_cord_layout, bottom_nav, nav_host_main, 0, -1, 0, 0, 0)
        }
    }
}
