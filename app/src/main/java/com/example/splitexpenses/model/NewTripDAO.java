package com.example.splitexpenses.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.splitexpenses.model.entity.NewTrip;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NewTripDAO {

    @Insert
    public long addNewTrip(NewTrip newTrip);

    @Update
    public void updateNewTrip(NewTrip newTrip);

    @Delete
    public void deleteNewTrip(NewTrip newTrip);

    @Query("SELECT * FROM ALL_TRIPS")
     public LiveData<List<NewTrip>> getAllNewTripsLiveData();

    @Query("SELECT * FROM ALL_TRIPS")
     public List<NewTrip> getAllNewTrips();


    @Query("SELECT * FROM ALL_TRIPS WHERE trip_id ==:id ")
     public NewTrip getTrip(long id);






}
