package com.example.conct_music.view.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.conct_music.R
import com.example.conct_music.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity(), DashboardContract.View {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_search -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.toSearchFragment)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.menu_favorites -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.toFavoritesFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }


    override fun initUI() {

    }

    override fun initDependences() {

    }

}
