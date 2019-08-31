package com.example.faridaziz.kopinema

import android.content.Context
import android.content.SharedPreferences

class SharePreference(app: Context) {
    private val TAG = this.javaClass.simpleName
    private val sharedPreferences = app.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)

    companion object {
        const val ID_BOARD = "ID_BOARD"
        const val USERNAME = "USERNAME"
        const val DEVICE_IS_ACTIVE = "DEVICE_IS_ACTIVE"
        const val DEVICE_ON_PROCESS = "DEVICE_ON_PROCESS"
        const val ON_QUEUE = "ON_QUEUE"
    }

    var idBoard: String
        get() = sharedPreferences.getString(ID_BOARD, "NONE")!!
        set(value) = share { it.putString(ID_BOARD, value) }

    var user: String
        get() = sharedPreferences.getString(USERNAME, "Anonymous")!!
        set(value) = share{ it.putString(USERNAME, value) }

    var deviceIsActive: Boolean
        get() = sharedPreferences.getBoolean(DEVICE_IS_ACTIVE, false)
        set(value) = share { it.putBoolean(DEVICE_IS_ACTIVE, value)}

    var deviceOnProcess: Boolean
        get() = sharedPreferences.getBoolean(DEVICE_ON_PROCESS, false)
        set(value) = share { it.putBoolean(DEVICE_ON_PROCESS, value)}

    var onQueue: Boolean
        get() = sharedPreferences.getBoolean(ON_QUEUE, false)
        set(value) = share { it.putBoolean(ON_QUEUE, value)}

    private val share: (callback: (it: SharedPreferences.Editor)-> Unit) -> Unit = { callback ->
        with(sharedPreferences.edit()) {
            callback(this)
            apply()
        }
    }
}