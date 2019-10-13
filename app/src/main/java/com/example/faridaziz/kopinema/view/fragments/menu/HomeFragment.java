package com.example.faridaziz.kopinema.view.fragments.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.SharePreference;
import com.example.faridaziz.kopinema.view.activities.SetRatioActivity;
import com.example.faridaziz.kopinema.view.activities.SettingActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
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
    ) { return inflater.inflate(R.layout.fragment_home, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Shared Preferences
        SharePreference pref = new SharePreference(getContext());

        // Binding View
        CardView recomendationCard = (CardView) view.findViewById(R.id.menu_recomendation);
        CardView listCard = (CardView) view.findViewById(R.id.menu_list);
        CardView customCard = (CardView) view.findViewById(R.id.menu_custom);
        TextView greeting = (TextView) view.findViewById(R.id.lbl_greeting);

        // Greet to the user.
        greeting.setText("Hai, "+ pref.getUser());

        // Add OnClick Listener
        recomendationCard.setOnClickListener(this);
        listCard.setOnClickListener(this);
        customCard.setOnClickListener(this);

        // Alert Dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        if (! pref.getIdBoard().equals("NONE")) {
            if (pref.getUser().equals("Anonymous")) {
                dialog.setTitle("Peringatan");
                dialog.setMessage("Username belum didaftarkan di aplikasi ini.");
                dialog.setPositiveButton("Setting", setPrefOnClick(SharePreference.USERNAME));
                dialog.setNegativeButton("kembali", backOnClick);
                dialog.create().show();
            }
        } else {
            dialog.setTitle("Peringatan");
            dialog.setMessage("ID Board belum didaftarkan di aplikasi ini.");
            dialog.setPositiveButton("Setting", setPrefOnClick(SharePreference.ID_BOARD));
            dialog.setNegativeButton("kembali", backOnClick);
            dialog.create().show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SetRatioActivity.class);

        switch (v.getId()){
            case R.id.menu_list:
                intent.putExtra(SetRatioActivity.ARG, 0);
                break;

            case R.id.menu_recomendation:
                intent.putExtra(SetRatioActivity.ARG, 1);
                break;

            case R.id.menu_custom:
                intent.putExtra(SetRatioActivity.ARG, 2);
                break;
        }

        startActivity(intent);
        getActivity().finish();
    }
}
