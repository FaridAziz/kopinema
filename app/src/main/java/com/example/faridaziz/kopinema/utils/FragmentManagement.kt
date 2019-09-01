package com.example.faridaziz.kopinema.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentManagement (val container: Int, val fragmentManager: FragmentManager) {
    fun replace(target: Fragment, args: Map<String, Any?>){
        val manager = fragmentManager.beginTransaction()
        val bundle = Bundle()

        args.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
            }
        }

        target.arguments = bundle
        manager.replace(container, target)
                .commit()
    }

    fun replace(target: Fragment) {
        val manager = fragmentManager.beginTransaction()

        manager.replace(container, target)
                .commit()
    }
}