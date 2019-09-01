package com.example.faridaziz.kopinema.view.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.models.Data
import com.example.faridaziz.kopinema.utils.FragmentManagement
import com.example.faridaziz.kopinema.utils.setWindowTransparent
import com.example.faridaziz.kopinema.view.fragments.CustomFragment
import com.example.faridaziz.kopinema.view.fragments.ListRatioFragment
import com.example.faridaziz.kopinema.view.fragments.RecommendationFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SetRatioActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    companion object {
        const val ARG = "KEY_ARG" }

    val sharedPref by lazy {
        SharePreference(this)}
    val fragmentManagement = FragmentManagement(
            R.id.set_ratio_container, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowTransparent(window)
        setContentView(R.layout.activity_set_ratio)

        if (sharedPref.onQueue) {
            startActivity(Intent(this, MainActivity::class.java).apply {
                this.putExtra(MainActivity.KEY_ARG, MainActivity.VALUE_ON_QUEUE) })
            finish()
        }

        fragmentManagement.replace(when (intent.getIntExtra(ARG, -1)) {
            0 -> ListRatioFragment()
            1 -> RecommendationFragment()
            2 -> CustomFragment()
            else -> CustomFragment()
        })
    }
}