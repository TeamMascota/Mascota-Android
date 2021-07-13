package org.mascota.ui

import android.util.Log
import org.mascota.R
import org.mascota.databinding.ActivityMainBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.calendar.CalendarFragment
import org.mascota.ui.view.home.HomeFragment
import org.mascota.ui.view.rainbow.RainbowFragment
import org.mascota.util.extension.replace

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val calendarFragment: CalendarFragment by lazy { CalendarFragment() }
    private val rainbowFragment: RainbowFragment by lazy { RainbowFragment() }

    override fun initView() {
        initSelectMenu()
        replaceHomeFragment()
        initBottomNavigation()

        Log.d("앙앙","dkd")
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_calendar -> {
                    replaceCalendarFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_home -> {
                    replaceHomeFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_rainbow -> {
                    replaceRainbowFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun initSelectMenu() {
        binding.bnvMain.selectedItemId = R.id.menu_home
    }

    private fun replaceCalendarFragment() {
        replace(R.id.container_main, calendarFragment)
    }

    private fun replaceHomeFragment() {
        replace(R.id.container_main, homeFragment)
    }

    private fun replaceRainbowFragment() {
        replace(R.id.container_main, rainbowFragment)
    }
}