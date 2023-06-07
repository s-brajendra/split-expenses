package com.example.splitexpenses.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitexpenses.R;
import com.example.splitexpenses.template_data.MemberCardData;

import java.util.ArrayList;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder1> {



    // Data
    private final Context context;



    // data that we will put in recycler view cards
    private final ArrayList<MemberCardData> memberCardDataList;


    public MembersAdapter(Context context, ArrayList<MemberCardData> memberCardDataList) {
        this.context = context;
        this.memberCardDataList = memberCardDataList;
    }



    // view Holder
    public class ViewHolder1 extends RecyclerView.ViewHolder{

        private final TextView memberName;



        public ViewHolder1(@NonNull View itemView) {
            super(itemView);


            memberName  = itemView.findViewById(R.id.memberName);

        }
    }



    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_card,parent,false);

        return new ViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        MemberCardData memberModel = memberCardDataList.get(position);
        holder.memberName.setText(memberModel.getMemberName());



        // will change this thing later
        // click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"" + "you choosed" + memberCardDataList.get(position).getMemberName(),Toast.LENGTH_SHORT).show();            }
        });

    }

    @Override
    public int getItemCount() {
        return memberCardDataList.size();
    }




}
