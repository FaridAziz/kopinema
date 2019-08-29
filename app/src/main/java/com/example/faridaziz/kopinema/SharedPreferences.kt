package com.example.faridaziz.kopinema

import android.content.Context
import android.util.Log
import com.example.faridaziz.kopinema.models.Data

class SharedPreferences(app: Context) {
    val TAG = this.javaClass.simpleName
    val sharedPreferences = app.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)

    companion object {
        const val ID_BOARD = "ID_BOARD"
        const val USERNAME = "USERNAME"
        const val DEVICE_IS_ACTIVE = "DEVICE_IS_ACTIVE"
        const val DEVICE_ON_PROCESS = "DEVICE_ON_PROCESS"
    }

    var idBoard: String
        get() = sharedPreferences.getString(ID_BOARD, "NONE")!!
        set(value) = store(ID_BOARD, value)

    var user: String
        get() = sharedPreferences.getString(USERNAME, "Anonymous")!!
        set(value) = store(USERNAME, value)

    var deviceIsActive: Boolean
        get() = sharedPreferences.getBoolean(DEVICE_IS_ACTIVE, false)
        set(value) = store(USERNAME, value)

    var deviceOnProcess: Boolean
        get() = sharedPreferences.getBoolean(DEVICE_ON_PROCESS, false)
        set(value) = store(USERNAME, value)

    private val store: (key: String, value: Any) -> Unit = { key, value ->
        with(sharedPreferences.edit()) {
            Log.d(TAG, "res : $value")
            Log.d(TAG, "res : ${ value is String}")

            when (value) {
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
            }

            apply()
        }
    }
}