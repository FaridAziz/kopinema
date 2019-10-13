package com.example.faridaziz.kopinema

import android.app.Application
import android.util.Log
import com.example.faridaziz.kopinema.models.Board
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class App
    : Application() {
    private val TAG = this.javaClass.simpleName

    companion object {
        const val DB = "database"
        const val USER = "user"
        const val BOARD = "board"
        const val QUEUE = "queue"
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}