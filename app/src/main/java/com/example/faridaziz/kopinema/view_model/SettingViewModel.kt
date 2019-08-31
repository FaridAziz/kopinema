package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.faridaziz.kopinema.repo.BoardRepository
import com.example.faridaziz.kopinema.view.activities.MainActivity

/**
 * @class [SettingViewModel]
 * Digunakan untuk melakukan operasi" yang berhubungan dengan :
 * data, jaringan, dll.
 */

class SettingViewModel(val app: Application) : AndroidViewModel(app) {
    private val repo = BoardRepository()

    fun sendSetting(ssid: String, password: String, owner: LifecycleOwner) {
        val params = mapOf(
                "ssid" to ssid,
                "password" to password
        )

        repo.send(params).observe(owner, Observer {
            if (it == "success") {
                Toast.makeText(app, "IoT Device Telah Berjalan. Terimakasih.",
                        Toast.LENGTH_SHORT)
                        .show()

                val activity = app.applicationContext as AppCompatActivity
                activity.startActivity(Intent(app, MainActivity::class.java))
                activity.finish()
            } else {
                Toast.makeText(
                        app, "Coba Ulangi Lagi.", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}