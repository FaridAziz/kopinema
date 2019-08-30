package com.example.faridaziz.kopinema.view.fragments.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.SharePreference;
import com.example.faridaziz.kopinema.view.activities.SettingActivity;
import com.example.faridaziz.kopinema.view_model.StatusViewModel;

public class StatusFragment extends Fragment {
    private String TAG = this.getClass().getSimpleName();

    private DialogInterface.OnClickListener setPrefOnClick (final String arg) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                intent.putExtra(SettingActivity.RECEIVE_DATA, arg);

                startActivity(intent);
            }
        };
    }

    private DialogInterface.OnClickListener backOnClick =
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            };

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) { return inflater.inflate(R.layout.fragment_status, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreted: OK");

        // Get Shared Preferences
        SharePreference pref = new SharePreference(getContext());

        // Binding View
        TextView did = (TextView) view.findViewById(R.id.lbl_device_id);

        // Show Device Id
        did.setText("Device Id \n" + pref.getIdBoard());

        // Alert Dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        if (pref.getIdBoard().equals("NONE")) {
            dialog.setTitle("Peringatan");
            dialog.setMessage("ID Board belum didaftarkan di aplikasi ini.");
            dialog.setPositiveButton("Setting", setPrefOnClick(SharePreference.ID_BOARD));
            dialog.setNegativeButton("kembali", backOnClick);
            dialog.create().show();
        } else {
            // ViewModel
            StatusViewModel viewModel = ViewModelProviders.of(this)
                    .get(StatusViewModel.class);

            // Binding View
            TextView status = (TextView) view.findViewById(R.id.txt_status);

            // Get Status Board
            viewModel.getStatus(status);
        }
    }
}
