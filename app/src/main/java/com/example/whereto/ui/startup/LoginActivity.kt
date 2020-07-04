package com.example.whereto.ui.startup

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.whereto.R

class LoginActivity : FragmentActivity(R.layout.activity_login) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //FULLSCREEN
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onStart() {
        super.onStart()

        navController = findNavController(R.id.nav_host_splash)
    }

}
