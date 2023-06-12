package com.example.splitexpenses.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.splitexpenses.db.entity.NewTrip;


@Database(entities = {NewTrip.class},version = 1)
public abstract class TripAppDatabase extends RoomDatabase {

    public abstract NewTripDAO getTripsDAO();
}
