package com.example.whereto.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.whereto.R
import com.example.whereto.util.SPLASH_DELAY
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onStart() {
        super.onStart()

        val handler = Handler()

        handler.postDelayed({
            navController = findNavController(R.id.nav_host_splash)
        }, SPLASH_DELAY)
    }

}
