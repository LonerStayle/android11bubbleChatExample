package com.soo.github.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.facebook.drawee.backends.pipeline.Fresco
import com.soo.github.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.product_nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }


}
