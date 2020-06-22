package com.example.whereto.ui.startup.splashscreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

import com.example.whereto.R
import com.example.whereto.util.SPLASH_DELAY
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : Fragment(R.layout.splash_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler()

        handler.postDelayed({
            splash_spin_wheel.visibility = View.GONE
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }, SPLASH_DELAY)
    }

}
