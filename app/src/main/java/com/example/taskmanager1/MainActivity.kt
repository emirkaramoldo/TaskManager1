package com.example.taskmanager1

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager1.data.local.Pref
import com.example.taskmanager1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pref : Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isShowed())
            navController.navigate(R.id.onBoardingFragment)


        if (FirebaseAuth.getInstance().currentUser?.uid.isNullOrEmpty()){
            navController.navigate(R.id.authFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment,
            )
        )
        val fragmentsWithoutBottomNav = setOf(
            R.id.acceptFragment,
            R.id.phoneFragment,
            R.id.onBoardingFragment,
            R.id.authFragment,
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (fragmentsWithoutBottomNav.contains(destination.id)){
                bottomNav.isVisible = false
                supportActionBar?.hide()
            } else{
                bottomNav.isVisible = true
                supportActionBar?.show()
            }
        }
        bottomNav.setupWithNavController(navController)
    }
}