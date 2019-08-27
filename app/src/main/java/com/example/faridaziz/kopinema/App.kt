package com.example.faridaziz.kopinema

import android.app.Application
import com.google.firebase.FirebaseApp

class App
    : Application() {

    companion object {
        const val DB = "database"
        const val USER = "user"
        const val BOARD = "board"
        const val QUEUE = "queue"
        const val SERVED = "served"
    }

    override fun onCreate() {
        super.onCreate()

        // Inisiasi Firebase
        FirebaseApp.initializeApp(this)
    }
}