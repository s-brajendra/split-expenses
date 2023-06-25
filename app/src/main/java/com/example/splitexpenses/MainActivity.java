package com.example.splitexpenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.splitexpenses.fragments.AnalysisFragment;
import com.example.splitexpenses.fragments.HomeFragment;
import com.example.splitexpenses.fragments.TripsFragment;
import com.example.splitexpenses.model.NewTripDAO;
import com.example.splitexpenses.model.TripAppDatabase;
import com.example.splitexpenses.model.TripRepository;
import com.example.splitexpenses.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{


    private int IsSignedIn = 0;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    TripsFragment tripsFragment = new TripsFragment();
    AnalysisFragment analysisFragment = new AnalysisFragment();
    TripRepository tripRepository;

    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // to show back button
//        // to avoid null pointer error
//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        }

        // null pointer error

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // simple toolbar.setTitle eont work as its priority is lower than given title in manifest
        getSupportActionBar().setTitle("Split Expenses");


        tripRepository = new TripRepository(getApplication());






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(IsSignedIn == 0){
            new MenuInflater(this).inflate(R.menu.option_menu_after_signed_in,menu);

        }
        else{
            new MenuInflater(this).inflate(R.menu.option_menu,menu);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_sign_in)
        {
            Log.i("TAGY","menu sign in working implemet it");
        }
        else if(itemId == R.id.menu_newAc)
        {
            Log.i("TAGY","menu create accoubt working implemet it");
        }

        else if(itemId == R.id.signed_settings)
        {
            Log.i("TAGY","implemet settings");
        }
        else if (itemId == R.id.signed_logout) {
            Log.i("TAGY","implemet LogOut");

        }


        return super.onOptionsItemSelected(item);
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