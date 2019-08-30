package com.example.faridaziz.kopinema.view.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.view.activities.SettingActivity
import com.example.faridaziz.kopinema.SharePreference
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment
    : Fragment() {
    companion object {
        const val ARG = "ARG" }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_setting, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments?.getInt(ARG, -1)

        fun attachFragment(data: String) {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra(SettingActivity.RECEIVE_DATA, data)

            startActivity(intent)
        }

        // Set username
        menu_username.setOnClickListener { attachFragment(SharePreference.USERNAME) }

        // Set Id Board
        menu_device_id.setOnClickListener { attachFragment(SharePreference.ID_BOARD) }

        // Set Wifi
        menu_device_network.setOnClickListener { attachFragment("WIFI") }
    }
}