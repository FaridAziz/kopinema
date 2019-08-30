package com.example.faridaziz.kopinema.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.faridaziz.kopinema.App;
import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.SharePreference;
import com.example.faridaziz.kopinema.models.Data;
import com.example.faridaziz.kopinema.models.Rasio;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;


public class RecommendationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) { return inflater.inflate(R.layout.fragment_recommendation, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharePreference sharedPref = new SharePreference(getContext());

        // Binding View
        EditText water = (EditText) view.findViewById(R.id.txt_ratio_water);
        EditText coffee = (EditText) view.findViewById(R.id.txt_ratio_coffee);
        Button start = (Button) view.findViewById(R.id.btn_start);

        Rasio rasio = new Rasio(
                Double.valueOf(water.getText().toString()),
                Double.valueOf(coffee.getText().toString()));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        final Data data = new Data (
                sharedPref.getIdBoard(), sharedPref.getUser(), timestamp.toString(),
                false, rasio);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get instance
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                // Read Realtime Database
                // Reference : /database/board
                database.getReference(App.DB).child(App.QUEUE)
                        .push()
                        .setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                getActivity().finish();
                            }
                        });
            }
        });
    }
}
