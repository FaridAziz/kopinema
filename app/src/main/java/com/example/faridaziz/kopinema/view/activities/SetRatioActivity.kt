package com.example.faridaziz.kopinema.view.activities

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.models.Data
import com.example.faridaziz.kopinema.view.fragments.CustomFragment
import com.example.faridaziz.kopinema.view.fragments.ListRatioFragment
import com.example.faridaziz.kopinema.view.fragments.RecommendationFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SetRatioActivity
    : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    companion object {
        const val ARG = "ARG"
    }

    val sharedPref by lazy {
        SharePreference(this)}
    val dialog by lazy {
        AlertDialog.Builder(this) }

    private val queueListener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) { Log.e(TAG, "Database Error") }

        override fun onDataChange(p0: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for (p in p0.children) {
                val value = p.getValue(Data::class.java) as Data

                if (value.id_board == sharedPref.idBoard) {
                    // TODO Testing
                    dialog.setTitle("Peringatan")
                            .setMessage("Aplikasi ini Sudah terdaftar dalam antrian")
                            .setNegativeButton("kembali", backOnClick)
                            .create().show()
                    finish()
                }
            }
        }
    }

    private val backOnClick = DialogInterface.OnClickListener { dialogInterface, i ->
        dialogInterface.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_set_ratio)

        val choice = intent.getIntExtra(ARG, -1)
        Log.d(TAG, "choice: $choice")
        val selectedFragment: Fragment = when (choice) {
            0 -> ListRatioFragment()
            1 -> RecommendationFragment()
            2 -> CustomFragment()
            else -> CustomFragment()
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.set_ratio_container, selectedFragment)
                .commit()
    }
}