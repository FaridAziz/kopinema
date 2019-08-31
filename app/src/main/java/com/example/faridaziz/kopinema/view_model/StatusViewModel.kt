package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import com.example.faridaziz.kopinema.App
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.models.Board
import com.example.faridaziz.kopinema.models.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StatusViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharePreference(app)

    fun getStatus(textView: TextView) {
        if (sharedPref.deviceIsActive) {
            if (sharedPref.deviceOnProcess) {
                textView.text = "Device sedang menyeduh kopi untuk Pengguna Lain"

                // Instance Database
                val database = FirebaseDatabase.getInstance()

                // Read Realtime Database
                // Reference : /database/queue
                database.getReference(App.DB).child(App.QUEUE)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) { Log.e(TAG, "Database Error") }

                            override fun onDataChange(p0: DataSnapshot) {
                                val value = p0.value.toString()
                                Log.d(TAG, "onDataChange : " + value)

                                if (value.contains(sharedPref.idBoard)) {
                                    sharedPref.onQueue = true
                                    textView.text = "Device Sedang menyeduh kopi Anda."
                                } else {
                                    sharedPref.onQueue = false
                                }
                            }
                        })
            } else {
                textView.text = "Device Siap Digunakan"
            }
        }
    }
}