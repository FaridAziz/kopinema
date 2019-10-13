package com.example.faridaziz.kopinema.models

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
data class Board (
        @PropertyName("id")
        var id: String? = "",

        @PropertyName("onProcess")
        var isOnProcess: Boolean? = true,

        @PropertyName("active")
        var active: Boolean? = true
)