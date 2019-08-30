package com.example.faridaziz.kopinema.models

import com.google.firebase.database.Exclude

data class Data (
        val id_board: String,
        val name: String,
        val time: String,
        val on_complete: Boolean,
        val rasio: Rasio
) {
    @Exclude
    fun toMap(): Map<String, Any?> = mapOf(
            "id_board" to id_board,
            "on_complete" to on_complete,
            "name" to name,
            "time" to time,
            "ratio" to rasio
    )
}