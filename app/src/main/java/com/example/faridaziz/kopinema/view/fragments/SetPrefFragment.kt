package com.example.faridaziz.kopinema.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharedPreferences
import kotlinx.android.synthetic.main.fragment_setpref.*

/**
 * @class [SetPrefFragment]
 * Digunakan untuk menampilkan UI
 * untuk melakukan perubahan data persistance SharedPreference.
 * Yaitu Digunakan untuk Mengubah Username dan Id Board.
 *
 */

class SetPrefFragment
    : Fragment() {
    companion object {
        const val ARG_ = "SET_APA_"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_setpref, container, false)

    /**
     *  Selalu kirimkan bundle untuk argument
     * jika ingin menggunakan fragment ini dimana key. harus [ARG_]
     * Dan Karena fragment ini digunakan untuk merubah data SharedPreference
     * yaitu username dan Id Board. maka valuenya pastikan menggunakan :
     * [SharedPreferences.USERNAME] atau [SharedPreferences.ID_BOARD]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = SharedPreferences(activity as AppCompatActivity)
        val set_ = arguments?.getString(ARG_) ?: ""

        // Set Text
        txt_set.text = "Masukkan ${ SharedPreferences.getString(set_)}"

        btn_set.setOnClickListener {
            if (set_ == "USERNAME")
                sharedPreferences.user = edt_set.text.toString()
            else
                sharedPreferences.idBoard = edt_set.text.toString()
        }
    }
}