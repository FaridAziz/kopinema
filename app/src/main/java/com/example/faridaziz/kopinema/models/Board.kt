package com.example.faridaziz.kopinema.models

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
class Board {
        @PropertyName("id")
        var id: String? = ""

        @PropertyName("isActive")
        var isActive: Boolean? = true

        @PropertyName("onProcess")
        var isOnProcess: Boolean? = true
}