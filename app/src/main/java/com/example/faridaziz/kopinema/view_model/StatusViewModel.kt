package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import com.example.faridaziz.kopinema.SharePreference

class StatusViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPreferences = SharePreference(app)

    fun getStatus(textView: TextView) {
        if (sharedPreferences.deviceIsActive) {
            if (sharedPreferences.deviceOnProcess) {
                textView.text = "Device Sedang Melakukan Proccess untuk Pengguna Lain"
            } else {
                textView.text = "Device Siap Digunakan"
            }
        }
    }
}