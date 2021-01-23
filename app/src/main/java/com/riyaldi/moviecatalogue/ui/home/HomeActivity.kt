package com.riyaldi.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityHomeBinding? = null
    private val binding get() = _activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupBottomNav()

    }

    private fun setupBottomNav() {
        val bottomNavigationView = binding?.bottomNavMain
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(
                    bottomNavigationView,
                    navHostFragment.navController
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}