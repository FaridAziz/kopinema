package com.example.faridaziz.kopinema

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPreferences(activity: AppCompatActivity) {
    val sharedPreferences = activity.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)

    companion object {
        const val ID_BOARD = "ID_BOARD"
        const val USERNAME = "USERNAME"

        val getString: (s: String)-> String = {s -> when(s) {
                ID_BOARD -> "Device ID"
                USERNAME -> "Username"
                else -> ""
            } }
    }

    var idBoard: String
        get() = sharedPreferences.getString(ID_BOARD, "NONE")!!
        set(value) = store(ID_BOARD, value)

    var user: String
        get() = sharedPreferences.getString(USERNAME, "Anonymous")!!
        set(value) = store(USERNAME, value)

    private val store: (key: String, value: String) -> Unit = { key, value ->
        sharedPreferences ?: with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
}