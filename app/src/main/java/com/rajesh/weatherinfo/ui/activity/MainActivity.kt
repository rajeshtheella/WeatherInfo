package com.rajesh.weatherinfo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rajesh.weatherinfo.R

class MainActivity : AppCompatActivity() {
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
    }

    fun hideActionBar() {
        supportActionBar?.hide()
    }

    fun showActionBar() {
        supportActionBar?.show()
    }

    @Suppress("DEPRECATION")
    fun setFullScreenMode() {
        window?.setFlags(View.SYSTEM_UI_FLAG_FULLSCREEN, View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        navController?.popBackStack()
    }
}