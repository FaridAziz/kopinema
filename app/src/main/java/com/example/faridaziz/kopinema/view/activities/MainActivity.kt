package com.example.faridaziz.kopinema.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.utils.FragmentManagement
import com.example.faridaziz.kopinema.view.fragments.menu.HomeFragment
import com.example.faridaziz.kopinema.view.fragments.menu.SettingFragment
import com.example.faridaziz.kopinema.view.fragments.menu.StatusFragment
import com.example.faridaziz.kopinema.view_model.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.faridaziz.kopinema.utils.setWindowTransparent
import com.example.faridaziz.kopinema.utils.showMessage


class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_ARG = "KEY_ARG"
        const val VALUE_START = "VALUE_START"
        const val VALUE_ON_QUEUE = "on_queue"
        const val VALUE_STATUS = "status"
    }

    private val TAG = this.javaClass.simpleName
    private val fragmentManagement = FragmentManagement(
            R.id.fragment_container, supportFragmentManager)
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowTransparent(window)
        setContentView(R.layout.activity_main)

        viewModel.listenBoard()
        fragmentManagement.replace(when(intent.getStringExtra(KEY_ARG) ?: "default") {
            VALUE_ON_QUEUE -> {
                val msg = R.string.warningOnQueue
                showMessage(this, msg)
                StatusFragment()
            }
            VALUE_START -> StatusFragment()
            VALUE_STATUS -> SettingFragment()
            else -> HomeFragment()
        })

        findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .setOnNavigationItemSelectedListener { item ->
                    fragmentManagement.replace(when (item.itemId) {
                        R.id.nav_home -> HomeFragment()
                        R.id.nav_status -> StatusFragment()
                        R.id.nav_setting -> SettingFragment()
                        else -> Fragment()
                    })

                    true
                }
    }
}
