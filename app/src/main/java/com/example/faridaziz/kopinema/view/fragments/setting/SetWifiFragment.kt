package com.example.faridaziz.kopinema.view.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.utils.showMessage
import com.example.faridaziz.kopinema.view_model.SettingViewModel
import kotlinx.android.synthetic.main.fragment_set_wifi.*

/**
 * class [SetWifiFragment]
 * Merupakan class yang menangani masalah
 * pada view.
 */

class SetWifiFragment
    : Fragment() {

    // Membuat Variabel dimana akan dilakukan inisialisasi
    // ketika pertama kali dipanggil.
    private val viewModel by lazy {
        ViewModelProviders.of(this)
                .get(SettingViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_set_wifi, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Event Listener
        btn_send.setOnClickListener {
            val ssid = txt_ssid.text.toString()
            val pass = txt_password.text.toString()

            if (ssid.isNotEmpty() && pass.isNotEmpty()) {
                // Send Data To Repository
                viewModel.sendSetting(ssid, pass, this)
            } else {
                showMessage(context!!, R.string.warningInputEmpty) }
        }
    }
}