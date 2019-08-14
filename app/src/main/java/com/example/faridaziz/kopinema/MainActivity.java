package com.example.faridaziz.kopinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView RekomendasiCard, PilihCard, CustomCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RekomendasiCard = findViewById(R.id.menu_rekomendasi);
        PilihCard = findViewById(R.id.menu_pilih);
        CustomCard = findViewById(R.id.menu_custom);

        RekomendasiCard.setOnClickListener(this);
        PilihCard.setOnClickListener(this);
        CustomCard.setOnClickListener(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_status:
                            selectedFragment = new StatusFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.menu_rekomendasi:{
                intent = new Intent(this,rekomendasiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_pilih:{
                intent = new Intent(this,pilihActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_custom:{
                intent = new Intent(this,customActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}

