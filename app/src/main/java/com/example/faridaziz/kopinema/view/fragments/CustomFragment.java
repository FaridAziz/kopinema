package com.example.faridaziz.kopinema.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

public class CustomFragment extends Fragment {

    private String water;
    private String coffee;

    private static final int WATER = 1;
    private static final int COFFEE = 2;

    private AdapterView.OnItemSelectedListener selectedListener(final int it) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();

                switch (it) {
                    case WATER : water = text;
                        break;

                    case COFFEE : coffee = text;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) { return inflater.inflate(R.layout.fragment_custom, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get SharedPreferences
        final SharePreference sharedPref = new SharePreference(getContext());

        // Binding View
        Spinner listWater = view.findViewById(R.id.spinner_water);
        Spinner listCoffee = view.findViewById(R.id.spinner_coffee);
        Button start = (Button) view.findViewById(R.id.btn_start);

        // Create Adapter For Spinner
        ArrayAdapter<CharSequence> waterAdapter = ArrayAdapter.createFromResource(
                getContext(), R.array.nomer, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> coffeeAdapter = ArrayAdapter.createFromResource(
                getContext(), R.array.nomer, android.R.layout.simple_spinner_item);

        // Inflate layout for Spinner Dropdown
        waterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter & set onItemSelectedListener
        listWater.setAdapter(waterAdapter);
        listCoffee.setAdapter(coffeeAdapter);
        listWater.setOnItemSelectedListener(selectedListener(WATER));
        listCoffee.setOnItemSelectedListener(selectedListener(COFFEE));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rasio rasio = new Rasio(
                        Double.valueOf(water),
                        Double.valueOf(coffee));

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                final Data data = new Data (
                        sharedPref.getIdBoard(), sharedPref.getUser(), timestamp.toString(),
                        false, rasio);

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
