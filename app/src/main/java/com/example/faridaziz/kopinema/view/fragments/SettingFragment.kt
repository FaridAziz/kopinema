package com.example.faridaziz.kopinema.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.view.activities.SettingActivity
import com.example.faridaziz.kopinema.SharedPreferences
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment
    : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_setting, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun attachFragment(data: String) {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra(SettingActivity.RECEIVE_DATA, data)

            startActivity(intent)
        }

        // Set username
        username.setOnClickListener { attachFragment(SharedPreferences.USERNAME) }

        // Set Id Board
        id_board.setOnClickListener { attachFragment(SharedPreferences.ID_BOARD) }

        // Set Wifi
        wifi.setOnClickListener { attachFragment("WIFI") }
    }
}