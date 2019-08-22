package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.faridaziz.kopinema.repo.BoardRepository

/**
 * @class [SettingViewModel]
 * Digunakan untuk melakukan operasi" yang berhubungan dengan :
 * data, jaringan, dll.
 */

class SettingViewModel(val app: Application)
    : AndroidViewModel(app) {
    private val repo = BoardRepository()

    fun sendSetting(ssid: String, password: String, owner: LifecycleOwner) {
        val params = mapOf(
                "ssid" to ssid,
                "password" to password
        )

        repo.send(params).observe(owner, Observer {
            if (it == "success") Toast.makeText(
                    app, "IoT Device Telah Berjalan. Terimakasih.", Toast.LENGTH_SHORT
                ).show()

            else  Toast.makeText(
                    app, "Coba Ulangi Lagi.", Toast.LENGTH_SHORT
            ).show()
        })
    }
}