package com.example.splitexpenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splitexpenses.adapters.MembersAdapter;
import com.example.splitexpenses.template_data.MemberCardData;

import java.util.ArrayList;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        btn = findViewById(R.id.addMemberButton);
        btnCreate = findViewById(R.id.createButton);
        editText = findViewById(R.id.editMember);
        textView = findViewById(R.id.txtCount);
        recyclerView = findViewById(R.id.addMemberRecyclerView);

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
                    for(int i = 0; i <  newTripActivityViewModel.getMembersArray().size(); i++ )
                    {
                        Toast.makeText(getApplication(),""+newTripActivityViewModel.getMembersArray().get(i).getMemberName(),Toast.LENGTH_SHORT).show();
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);

                }


            }
        });







    }
}