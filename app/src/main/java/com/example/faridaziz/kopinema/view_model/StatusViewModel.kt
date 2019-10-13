package com.example.faridaziz.kopinema.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.faridaziz.kopinema.App
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StatusViewModel(val app: Application) : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharePreference(app)

    // Instance Database
    val database = FirebaseDatabase.getInstance()
    val status = MutableLiveData<String>()

    fun getStatusDevice(owner: LifecycleOwner): LiveData<String> {
        MainViewModel.board.observe(owner, Observer {
            if (sharedPref.deviceIsActive) {
                if (sharedPref.deviceOnProcess) {
                    status.postValue(string(R.string.statusOnQueue))
                    getDataQueue()
                } else {
                    status.postValue(string(R.string.statusReady)) }
            } else {
                status.postValue(string(R.string.statusNotReady)); }
        })

        return status
    }

    fun getDataQueue() {
        // Read Realtime Database
        // Reference : /database/queue
        database.getReference(App.DB).child(App.QUEUE)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e(TAG, "Database Error") }

                    override fun onDataChange(p0: DataSnapshot) {
                        val value = p0.value.toString()
                        Log.d(TAG, "onDataChange : $value")

                        if (value.contains(sharedPref.user)) {
                            sharedPref.onQueue = true
                            status.postValue(string(R.string.statusOnProcess))
                        } else {
                            sharedPref.onQueue = false }
                    }
                })
    }

    private fun string(resId: Int) = app.resources.getString(resId)
}