package com.example.splitexpenses.model;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.splitexpenses.model.entity.NewTrip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {NewTrip.class},version = 1)
public abstract class TripAppDatabase extends RoomDatabase {

    public abstract NewTripDAO getTripsDAO();





    // for ensuring singleton we created getinstance method
    private static TripAppDatabase instance;
    public static synchronized TripAppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TripAppDatabase.class, "TripApp_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
//            .addCallback(roomCallback)
            Log.i("TAGY"," db instance was null and created");

        }
        Log.i("TAGY","db instance returned");

        return instance;

    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insert data when db is created...
            InitializeData();


        }
    };
    private static void InitializeData() {

        NewTripDAO newTripDAO = instance.getTripsDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {


                NewTrip nt = new NewTrip();
                nt.setTripAdmin("meAdmin");
                nt.setId(1);
                nt.setTotalMembers(5);
                newTripDAO.addNewTrip(nt);

                Log.i("TAGY","InitializeData done");

            }
        });




    }


}
