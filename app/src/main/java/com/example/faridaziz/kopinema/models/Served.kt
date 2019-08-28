package com.example.faridaziz.kopinema.models

data class Served(
        val id: String,
        val id_board: String,
        val name: String,
        val time: String,
        val rasio: Rasio,
        val is_complete: Boolean
)