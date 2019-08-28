package com.example.faridaziz.kopinema.models

import com.google.firebase.database.PropertyName

class Board {
        @PropertyName("id")
        var id: String? = null

        @PropertyName("is_active")
        var isActive: Boolean = false

        @PropertyName("on_process")
        var isOnProcess: Boolean = false
}