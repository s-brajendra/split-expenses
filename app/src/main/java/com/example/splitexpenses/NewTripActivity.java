package com.example.splitexpenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splitexpenses.adapters.MembersAdapter;
import com.example.splitexpenses.db.TripAppDatabase;
import com.example.splitexpenses.db.entity.NewTrip;
import com.example.splitexpenses.fragments.HomeFragment;
import com.example.splitexpenses.template_data.MemberCardData;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewTripActivity extends AppCompatActivity {

    Button btn, btnCreate;
    EditText editText;
    NewTripActivityViewModel newTripActivityViewModel;
    TextView textView;
    // recycler view
    RecyclerView recyclerView;
    //data
    ArrayList<MemberCardData> tripList;
    // adapter
    MembersAdapter adapter;
    public TripAppDatabase tripAppDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        btn = findViewById(R.id.addMemberButton);
        btnCreate = findViewById(R.id.createButton);
        editText = findViewById(R.id.editMember);
        textView = findViewById(R.id.txtCount);
        recyclerView = findViewById(R.id.addMemberRecyclerView);


        tripAppDatabase  = Room.databaseBuilder(
                getApplicationContext(),
                TripAppDatabase.class,
                "TripsDB"

        ).allowMainThreadQueries().build();


        ArrayList<MemberCardData> memeberList = new ArrayList<>();

        newTripActivityViewModel = new ViewModelProvider(this).get(NewTripActivityViewModel.class);
        adapter = new MembersAdapter(getApplicationContext(),newTripActivityViewModel.getMembersArray());


        LiveData<Integer> count = newTripActivityViewModel.getInitialCounter();
        count.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText("You Clicked Me: "+integer + " times");
                editText.setText("");
            }




        });






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str =String.valueOf(editText.getText());

                if(str.length() != 0)
                {
                    // Getting the Current Count
                    newTripActivityViewModel.getAllMembers(str);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);

                }



                for (int i = 0; i < newTripActivityViewModel.getMembersName().size();i++)
                {
                    Toast.makeText(getApplicationContext(), ""+ newTripActivityViewModel.getMembersName().get(i),Toast.LENGTH_SHORT ).show();
                }


            }
        });


        // onclick listner for create trip button, members provided
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

 // for genrating id
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                String datetime = ft.format(dNow);


                // sending data to Home Fragment
                Intent i = new Intent();
                ArrayList<String>  membersName  = newTripActivityViewModel.getMembersName();
               String[] str = new String[membersName.size()];

                for (int j = 0; j < membersName.size(); j++) {
                    str[j] = membersName.get(j);
                }
                i.putExtra("membersName",str);
                setResult(RESULT_OK,i);
                finish();



            }
        });







    }
}