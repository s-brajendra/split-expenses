package com.example.splitexpenses.model.entity;




import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "ALL_TRIPS")
public class NewTrip extends BaseObservable{


    @ColumnInfo(name = "trip_admin")
    private String tripAdmin;


    @ColumnInfo(name = "total_members")
    private int totalMembers;

    @ColumnInfo(name = "trip_id")
    @PrimaryKey(autoGenerate = true)
    private long id;


    @Ignore
    public NewTrip(){}


    public NewTrip(String tripAdmin, int totalMembers, long id) {
        this.tripAdmin = tripAdmin;
        this.totalMembers = totalMembers;
        this.id = id;
    }

    @Bindable
    public String getTripAdmin() {
        return tripAdmin;
    }

    public void setTripAdmin(String tripAdmin) {

        this.tripAdmin = tripAdmin;
        notifyPropertyChanged(BR.tripAdmin);

    }

    @Bindable
    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
        notifyPropertyChanged(BR.totalMembers);
    }
    @Bindable
    public long getId() {
        return id;

    }

    public void setId(int id) {

        this.id = id;
        notifyPropertyChanged(BR.id);



    }
}









