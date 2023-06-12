package com.example.splitexpenses;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitexpenses.template_data.MemberCardData;

import java.util.ArrayList;

public class NewTripActivityViewModel extends ViewModel {


    ArrayList<MemberCardData> membersArray = new ArrayList<>();


    ArrayList<String> membersName = new ArrayList<>();
    private int totalMembers;

    public ArrayList<String> getMembersName() {
        return membersName;
    }

    public int getTotalMembers() {
        totalMembers = membersArray.size();
        return totalMembers;
    }


    public void setCounter(int counter) {
        this.counter = counter;
    }

    private int counter = 40;

    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    // When the app first launched
    public MutableLiveData<Integer> getInitialCounter(){
        countLiveData.setValue(counter);
        return countLiveData;
    }


    // When user clicks the button
    public ArrayList<MemberCardData> getAllMembers(String str){
        membersName.add(str);
        MemberCardData temp = new MemberCardData(str);
        membersArray.add(temp);
        counter++;
        countLiveData.setValue(counter);
        return membersArray;
    }

    public ArrayList<MemberCardData> getMembersArray() {
        return membersArray;
    }
}
