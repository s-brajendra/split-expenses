package com.example.splitexpenses.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.splitexpenses.model.TripRepository;
import com.example.splitexpenses.model.entity.NewTrip;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private TripRepository tripRepository;

    // for recycler view
    private LiveData<List<NewTrip>> trips;
    private List<NewTrip> tripList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
    }

    public LiveData<List<NewTrip>> getAllTripsLiveData()
    {
        trips = tripRepository.getAllTripsLiveData();
        return trips;
    }

    public List<NewTrip> getAllTrips()
    {
        tripList = tripRepository.getAllTrips();
        return tripList;
    }


    public void addNewTrip(NewTrip newTrip)
    {
        tripRepository.addNewTrip(newTrip);
    }
}
