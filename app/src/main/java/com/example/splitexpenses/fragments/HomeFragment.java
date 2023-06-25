package com.example.splitexpenses.fragments;

import static java.lang.Long.parseLong;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.splitexpenses.MainActivity;
import com.example.splitexpenses.NewTripActivity;
import com.example.splitexpenses.R;
import com.example.splitexpenses.model.NewTripDAO;
import com.example.splitexpenses.model.TripAppDatabase;
import com.example.splitexpenses.model.TripRepository;
import com.example.splitexpenses.model.entity.NewTrip;
import com.example.splitexpenses.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {



    FloatingActionButton fab;
    RecyclerView recyclerView;

//    private MainActivityViewModel mainActivityViewModel;

    TripRepository tripRepository;
private TripAppDatabase tripAppDatabase;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        tripRepository = new TripRepository(getActivity().getApplication());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =    inflater.inflate(R.layout.fragment_home, container, false);

        Log.i("TAGY","ON create view called Home fragment");

//        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        tripAppDatabase = TripAppDatabase.getInstance(view.getContext());

        recyclerView = view.findViewById(R.id.recycler_trips_list);

         fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSomeActivityForResult();
            }
        });


        return view;
    }





    // starting activity for result;
    public void openSomeActivityForResult() {
        Intent intent = new Intent(getContext(), NewTripActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        int totalMembers = data.getIntExtra("totalMembers",0);
                        String []  members = new String[totalMembers];
                        members = data.getStringArrayExtra("memData");
                         //
                        Log.i("TAGY","passin worked " + members[0]);
                        // adding data in database



                        // for genrating id
                        Date dNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                        String datetime = ft.format(dNow);
                        Log.i("TAGY","admin: "+members[0] + "  total: " +members.length+" id:" + datetime );

                        NewTrip tp = new NewTrip(members[0].toString(),members.length,parseLong(datetime));
                        Log.i("TAGY","member0: "+tp.getTripAdmin() + " id: " + tp.getId() + " total: "+ tp.getTotalMembers());


                        tripRepository.addNewTrip(tp);
                        Log.i("TAGY","trip added ");


//                        Log.i("TAGY","" +tripRepository.getNewTripDAO().getAllNewTrips().get(0));






                    }
                }
            });


}
