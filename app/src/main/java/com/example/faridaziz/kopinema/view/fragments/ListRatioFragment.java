package com.example.faridaziz.kopinema.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.view.adapters.ListRatioAdapter;
import com.example.faridaziz.kopinema.models.ItemRatio;

import java.util.ArrayList;

public class ListRatioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) { return inflater.inflate(R.layout.fragment_list_ratio, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ItemRatio> pilihList = new ArrayList<>();
        RecyclerView mRecycleView = view.findViewById(R.id.recycleView);
        RecyclerView.Adapter mAdapter = new ListRatioAdapter(pilihList);

        // Set Data
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:13", "1 Gram kopi : 13 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:15", "1 Gram kopi : 15 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:17", "1 Gram kopi : 17 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:18", "1 Gram kopi : 18 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:20", "1 Gram kopi : 20 Gram air"));

        // Set Data to RecyclerView
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.setAdapter(mAdapter);
    }
}
