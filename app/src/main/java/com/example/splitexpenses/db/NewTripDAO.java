package com.example.splitexpenses.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.splitexpenses.db.entity.NewTrip;

import java.util.List;

@Dao
public interface NewTripDAO {

    @Insert
    long addNewTrip(NewTrip newTrip);

    @Update
    void updateNewTrip(NewTrip newTrip);

    @Delete
    void deleteNewTrip(NewTrip newTrip);

    @Query("SELECT * FROM ALL_TRIPS ")
    List<NewTrip> getAllNewTrips();


    @Query("SELECT * FROM ALL_TRIPS WHERE trip_id ==:id ")
    NewTrip getTrip(long id);






}
