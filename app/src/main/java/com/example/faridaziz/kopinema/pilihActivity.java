package com.example.faridaziz.kopinema;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class    pilihActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih);

        ArrayList<pilihItem> pilihList = new ArrayList<>();
        pilihList.add(new pilihItem(R.drawable.kopi, "1:13", "1 Gram kopi : 13 Gram air"));
        pilihList.add(new pilihItem(R.drawable.kopi, "1:15", "1 Gram kopi : 15 Gram air"));
        pilihList.add(new pilihItem(R.drawable.kopi, "1:17", "1 Gram kopi : 17 Gram air"));
        pilihList.add(new pilihItem(R.drawable.kopi, "1:18", "1 Gram kopi : 18 Gram air"));
        pilihList.add(new pilihItem(R.drawable.kopi, "1:20", "1 Gram kopi : 20 Gram air"));

        mRecycleView = findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new pilihAdapter(pilihList);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}
