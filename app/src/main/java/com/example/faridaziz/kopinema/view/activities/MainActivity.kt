package com.example.faridaziz.kopinema.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.App
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.models.Board
import com.example.faridaziz.kopinema.view.fragments.menu.HomeFragment
import com.example.faridaziz.kopinema.view.fragments.menu.SettingFragment
import com.example.faridaziz.kopinema.view.fragments.menu.StatusFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    companion object {
        const val ARG = "ARG"
        const val START = "START"
        const val ON_QUEUE = "on_queue"
    }

    val sharedPref by lazy {
        SharePreference(this) }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment = when (item.itemId) {
            R.id.nav_home -> HomeFragment()
            R.id.nav_status -> StatusFragment()
            R.id.nav_setting -> SettingFragment()
            else -> Fragment()
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get Argument from Intent
        // Get Instance Realtime Database
        val arg = intent.getStringExtra(ARG) ?: "default"
        val database = FirebaseDatabase.getInstance()

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        val fragment = when(arg) {
            ON_QUEUE -> {
                Toast.makeText(MainActivity@this, "Tidak dapat melakukan operasi ini karena anda masuk dalam antrian.", Toast.LENGTH_SHORT)
                        .show()
                StatusFragment()
            }

            START -> StatusFragment()

            "status" -> SettingFragment()
            else -> HomeFragment()
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

        // Read Realtime Database
        // Reference : /database/board
        database.getReference(App.DB).child(App.BOARD)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) { Log.e(TAG, "Database Error") }

                    override fun onDataChange(p0: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (data in p0.children) {
                            val value = data.getValue(Board::class.java)

                            if (value?.id == sharedPref.idBoard) {
                                Log.d(TAG, "onCreate: is active : "+ value.isActive)
                                Log.d(TAG, "onCreate: is on process : "+ value.isOnProcess)

                                sharedPref.deviceIsActive = value.isActive ?: false
                                sharedPref.deviceOnProcess = value.isOnProcess ?: false
                            }
                        }
                    }
                })
    }
}
