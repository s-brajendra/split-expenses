package com.example.splitexpenses.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ALL_TRIPS")
public class NewTrip {


    @ColumnInfo(name = "trip_admin")
    private String tripAdmin;


    @ColumnInfo(name = "total_members")
    private int totalMembers;

    @ColumnInfo(name = "trip_id")
    @PrimaryKey(autoGenerate = true)
    private int id;


    @Ignore
    public NewTrip(){}


    public NewTrip(String tripAdmin, int totalMembers, int id) {
        this.tripAdmin = tripAdmin;
        this.totalMembers = totalMembers;
        this.id = id;
    }

    public String getTripAdmin() {
        return tripAdmin;
    }

    public void setTripAdmin(String tripAdmin) {
        this.tripAdmin = tripAdmin;
    }


    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}









