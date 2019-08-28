package com.example.faridaziz.kopinema.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faridaziz.kopinema.R;
import com.example.faridaziz.kopinema.view.adapters.ListRatioAdapter;
import com.example.faridaziz.kopinema.models.ItemRatio;

import java.util.ArrayList;

public class ListRatioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ratio);

        ArrayList<ItemRatio> pilihList = new ArrayList<>();
        RecyclerView mRecycleView = findViewById(R.id.recycleView);
        RecyclerView.Adapter mAdapter = new ListRatioAdapter(pilihList);

        // Set Data
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:13", "1 Gram kopi : 13 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:15", "1 Gram kopi : 15 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:17", "1 Gram kopi : 17 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:18", "1 Gram kopi : 18 Gram air"));
        pilihList.add(new ItemRatio(R.drawable.ic_home, "1:20", "1 Gram kopi : 20 Gram air"));

        // Set Data to RecyclerView
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
    }
}
