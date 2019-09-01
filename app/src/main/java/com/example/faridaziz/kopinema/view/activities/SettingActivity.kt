package com.example.faridaziz.kopinema.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.utils.FragmentManagement
import com.example.faridaziz.kopinema.utils.setWindowTransparent
import com.example.faridaziz.kopinema.view.fragments.setting.SetUserOrDIdFragment
import com.example.faridaziz.kopinema.view.fragments.setting.SetWifiFragment

class SettingActivity : AppCompatActivity() {
    companion object {
        const val RECEIVE_DATA = "DATA" }

    val fragmentManagement = FragmentManagement(
            R.id.wrapper, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowTransparent(window)
        setContentView(R.layout.activity_setting)

        val arg = intent.getStringExtra(RECEIVE_DATA)

        fragmentManagement.replace(when(arg) {
                SharePreference.ID_BOARD, SharePreference.USERNAME -> SetUserOrDIdFragment()
                "WIFI" -> SetWifiFragment()
                else -> Fragment()
            }, mapOf<String, Any>(SetUserOrDIdFragment.ARG_ to arg)
        )
    }
}