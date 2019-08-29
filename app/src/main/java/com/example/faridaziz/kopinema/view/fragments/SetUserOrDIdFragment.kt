package com.example.faridaziz.kopinema.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharedPreferences

/**
 * @class [SetUserOrDIdFragment]
 * Digunakan untuk menampilkan UI
 * untuk melakukan perubahan data persistance SharedPreference.
 * Yaitu Digunakan untuk Mengubah Username dan Id Board.
 *
 */

class SetUserOrDIdFragment
    : Fragment() {
    private val TAG = this.javaClass.simpleName

    companion object {
        const val ARG_ = "SET_APA_"
    }

    private var _set_: String? = null
    val sharedPreferences by lazy {
        SharedPreferences(context!!) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        _set_ = arguments?.getString(ARG_) ?: ""
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
            if (_set_ == "USERNAME")
                R.layout.fragment_set_username
            else
                R.layout.fragment_set_device_id
            ,container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.btn_set)
        val edt: EditText = view.findViewById(R.id.edt_set)

        // Set OnClick Listener
        btn.setOnClickListener {
            val data = edt.text.toString().trim()
            Log.i(TAG, "onViewCreted: $_set_")

            if (data.isNotEmpty()) {
                if (_set_ == "USERNAME") {
                    sharedPreferences.user = data
                    Log.i(TAG, "onViewCreted: " + sharedPreferences.user)
                } else {
                    sharedPreferences.idBoard = data
                    Log.i(TAG, "onViewCreted: " + sharedPreferences.idBoard)
                }
            } else {
                Toast.makeText(context, "Input Tidak boleh Kosong", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}