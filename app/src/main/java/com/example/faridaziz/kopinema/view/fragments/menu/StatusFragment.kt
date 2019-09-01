package com.example.faridaziz.kopinema.view.fragments.menu

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.faridaziz.kopinema.R
import com.example.faridaziz.kopinema.SharePreference
import com.example.faridaziz.kopinema.view.activities.SettingActivity
import com.example.faridaziz.kopinema.view_model.StatusViewModel

class StatusFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StatusViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_status, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreted: OK")

        // Get Shared Preferences
        val pref = SharePreference(context!!)

        // Binding View
        val did = view.findViewById(R.id.lbl_device_id) as TextView
        val status = view.findViewById(R.id.txt_status) as TextView

        // Show Device Id
        did.text = "Device Id \n" + pref.idBoard

        // Alert Dialog
        val dialog = AlertDialog.Builder(activity)

        if (pref.idBoard == "NONE") {
            dialog.setTitle("Peringatan")
            dialog.setMessage("ID Board belum didaftarkan di aplikasi ini.")
            dialog.setPositiveButton("Setting"){ _, _ ->
                val intent = Intent(context, SettingActivity::class.java)
                intent.putExtra(SettingActivity.RECEIVE_DATA, SharePreference.ID_BOARD)
                startActivity(intent) }
            dialog.setNegativeButton("kembali") { dialogInterface, _ ->
                dialogInterface.dismiss() }
            dialog.create().show()
        } else {
            viewModel.getStatusDevice(this)
                    .observe(this, Observer { s -> status.text = s }) }
    }
}
