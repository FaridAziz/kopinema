package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.faridaziz.kopinema.App
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.models.Board
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        val board = MutableLiveData<Board>() }

    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharePreference(app)
    private val database = FirebaseDatabase.getInstance()

    fun listenBoard() {
        // Read Realtime Database
        // Reference : /database/board
        database.getReference(App.DB).child(App.BOARD)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e(TAG, "Database Error") }

                    override fun onDataChange(p0: DataSnapshot) {
                        Log.d(TAG, "onDataChange : ${ p0.value }")

                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (data in p0.children) {
                            val value = data.getValue(Board::class.java)

                            if (value?.id == sharedPref.idBoard) {
                                board.postValue(value)
                                sharedPref.deviceIsActive = value.active ?: false
                                sharedPref.deviceOnProcess = value.isOnProcess ?: false
                            }
                        }
                    }
                })
    }
}