package com.example.fairfare

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        if (savedInstanceState == null) {
            frag(HomeFragment(), false)
        }

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        navigation = findViewById(R.id.bottomNavigation)
        navigation.itemIconTintList = null
        navigation.setOnItemSelectedListener { onNavigationItemSelected(it) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val selectedFragment: Fragment = when (menuItem.itemId) {
            R.id.home -> HomeFragment()
            R.id.time ->TrackFragment()
            R.id.calander ->HistoryFragment()
            else ->AboutUsFragment()
        }
        frag(selectedFragment, false)
        return true
    }

    private fun frag(fragment: Fragment, flag: Boolean) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()


        if (flag) {
            fragmentTransaction.add(R.id.containeer, fragment)
        } else {
            fragmentTransaction.replace(R.id.containeer, fragment)
        }
        fragmentTransaction.commit()
    }
}
