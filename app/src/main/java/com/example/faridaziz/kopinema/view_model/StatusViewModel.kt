package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import com.example.faridaziz.kopinema.App
import com.example.faridaziz.kopinema.SharedPreferences
import com.example.faridaziz.kopinema.models.Board
import com.example.faridaziz.kopinema.models.BoardJ
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StatusViewModel(app: Application)
    : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPreferences = SharedPreferences(app)

    private val statusBoardListener = fun (v: TextView) = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) { Log.e(TAG, "Database Error") }

        override fun onDataChange(p0: DataSnapshot) {
            var board: Board? = null

            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for (data in p0.children) {
                val value = data.getValue(BoardJ::class.java) as BoardJ
                Log.d(TAG, "Test :  " + value.id + " : " + value.isActive + " : " + value.isOnProcess)

//                if (value.id == sharedPreferences.idBoard) board = value
            }

            if (board?.isActive == true) {
                if (board.isOnProcess == true)
                    v.text = "Device Sedang Melakukan Proses"
                else
                    v.text = "Device Siap digunakan"
            }

        }
    }


    fun getStatus(textView: TextView) {
        // Get Instance Realtime Database
        val database = FirebaseDatabase.getInstance()

        // Read Realtime Database
        // Reference : /database/board
        database.getReference(App.DB).child(App.BOARD)
                .addValueEventListener(statusBoardListener(textView))
    }
}