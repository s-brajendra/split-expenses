package com.example.splitexpenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.splitexpenses.fragments.AnalysisFragment;
import com.example.splitexpenses.fragments.HomeFragment;
import com.example.splitexpenses.fragments.TripsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{


    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    TripsFragment tripsFragment = new TripsFragment();
    AnalysisFragment analysisFragment = new AnalysisFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();
                return true;
            case R.id.trips:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,tripsFragment).commit();
                return true;

            case R.id.analysis:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,analysisFragment).commit();
                return true;


        }
        return false;
    }
}