package com.challenge.githubrepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.challenge.githubrepo.actionbar.ShowUpButtonListener
import com.challenge.githubrepo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ShowUpButtonListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun showUpButton() {
        val navController = this.findNavController(R.id.host_fragment)
        val listener = AppBarConfiguration.OnNavigateUpListener { navController.navigateUp() }
        val appBar = AppBarConfiguration.Builder().setFallbackOnNavigateUpListener(listener).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBar)
    }

    override fun hideUpButton() {
        val navController = this.findNavController(R.id.host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.host_fragment)
        if (!navController.navigateUp()) {
            onBackPressed()
        }
        return navController.navigateUp()
    }
}