package com.example.faridaziz.kopinema.view.fragments;

import android.content.Intent;
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
import com.example.faridaziz.kopinema.view.activities.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class RecommendationFragment extends Fragment {

    public static final String ARG = "KEY_ARG";

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

        // Get SharedPreferences Data
        final SharePreference sharedPref = new SharePreference(getContext());

        List<Integer> args = new ArrayList<>();

        if (getArguments() != null) {
            args = getArguments().getIntegerArrayList(ARG);
        }

        // Binding View
        final EditText water = (EditText) view.findViewById(R.id.txt_ratio_water);
        final EditText coffee = (EditText) view.findViewById(R.id.txt_ratio_coffee);
        Button start = (Button) view.findViewById(R.id.btn_start);

        if (args.size() > 0) {
            water.setText(""+ args.get(0));
            coffee.setText(""+ args.get(1));
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get instance
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                Rasio rasio = new Rasio(
                        Double.valueOf(water.getText().toString()),
                        Double.valueOf(coffee.getText().toString()));

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                final Data data = new Data (
                        sharedPref.getIdBoard(), sharedPref.getUser(), timestamp.toString(),
                        false, rasio);

                // Write Realtime Database
                // Reference : /database/queue
                database.getReference(App.DB).child(App.QUEUE)
                        .push()
                        .setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.putExtra(MainActivity.KEY_ARG, MainActivity.VALUE_START);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });
            }
        });
    }
}
