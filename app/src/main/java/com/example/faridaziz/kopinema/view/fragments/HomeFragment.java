package com.example.faridaziz.kopinema.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.view.activities.CustomActivity;
import com.example.faridaziz.kopinema.view.activities.ListRatioActivity;
import com.example.faridaziz.kopinema.view.activities.RecommendationActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

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

        // Binding View
        CardView recomendationCard = (CardView) view.findViewById(R.id.menu_recomendation);
        CardView listCard = (CardView) view.findViewById(R.id.menu_list);
        CardView customCard = (CardView) view.findViewById(R.id.menu_custom);

        // Add OnClick Listener
        recomendationCard.setOnClickListener(this);
        listCard.setOnClickListener(this);
        customCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.menu_list:
                intent = new Intent(getActivity(), ListRatioActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_recomendation:
                intent = new Intent(getActivity(), RecommendationActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_custom:
                intent = new Intent(getActivity(), CustomActivity.class);
                startActivity(intent);
                break;
        }
    }
}
