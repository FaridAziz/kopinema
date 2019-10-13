package com.example.faridaziz.kopinema.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast


fun showMessage(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}

fun showMessage(ctx: Context, msg: Int) {
    val message = ctx.resources.getString(msg)
    Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
}

fun setWindowTransparent(window: Window) {
    if (Build.VERSION.SDK_INT >= 21) {
        // Get Window Android
        val window = window

        // Set Fullscreen
        // change status bar color to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}