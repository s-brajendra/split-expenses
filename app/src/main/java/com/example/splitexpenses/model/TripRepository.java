package com.example.splitexpenses.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.splitexpenses.model.entity.NewTrip;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TripRepository {
    public NewTripDAO getNewTripDAO() {
        return newTripDAO;
    }

    private NewTripDAO newTripDAO;

    LiveData<List<NewTrip>> trips;

    // constructor
    public TripRepository(Application application) {
        TripAppDatabase tripAppDatabase = TripAppDatabase.getInstance(application);
        newTripDAO = tripAppDatabase.getTripsDAO();
    }



    public void addNewTrip(NewTrip newTrip){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                newTripDAO.addNewTrip(newTrip);
            }
        });
    }

    public LiveData<List<NewTrip>> getAllTripsLiveData() {
        return newTripDAO.getAllNewTripsLiveData();
    }

    public List<NewTrip> getAllTrips() {
        return newTripDAO.getAllNewTrips();
    }



}
