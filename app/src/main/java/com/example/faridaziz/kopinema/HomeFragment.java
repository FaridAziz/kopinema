package com.example.faridaziz.kopinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements View.OnClickListener{
    View view;
    private CardView RekomendasiCard, PilihCard, CustomCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_home, container, false);
        RekomendasiCard = (CardView) view.findViewById(R.id.menu_rekomendasi);
        RekomendasiCard.setOnClickListener(this);
        PilihCard = (CardView) view.findViewById(R.id.menu_pilih);
        PilihCard.setOnClickListener(this);
        CustomCard = (CardView) view.findViewById(R.id.menu_custom);
        CustomCard.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.menu_rekomendasi:{
                intent = new Intent(getActivity(),rekomendasiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_pilih:{
                intent = new Intent(getActivity(),pilihActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_custom:{
                intent = new Intent(getActivity(),customActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
