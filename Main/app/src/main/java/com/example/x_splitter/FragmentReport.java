package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FragmentReport extends Fragment {

    View view;
    Context context;
    String user1;
    String user2;
    Double tempAmt;
    Double amountInvested;
    Double amountToGet;
    Double amountToPay;
    String currentEventID;
    String currentGroupName;
    String currentGroupID;

    public FragmentReport(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_report_fragment, container, false);

//        Intent i = getActivity().getIntent();
//        user1 = i.getStringExtra("user1");
//         user2 = i.getStringExtra("user2");
//         tempAmt = Double.parseDouble(i.getStringExtra("tempAmt"));



//        Intent intent = getActivity().getIntent();
Bundle bundle = this.getArguments();

        currentEventID = AdapterHomeEvent.id;
        currentGroupID = AdapterHomeEvent.grpId;
        currentGroupName = AdapterHomeEvent.grpName;

//    currentEventID = bundle.getString("currentEventID");
//    currentGroupName = bundle.getString("currentGroupName");
//    currentGroupID = bundle.getString("currentGroupID");

        System.out.println("Mumy:"+ currentEventID);
//        System.out.println("Mumy:"+ currentEventName);

        System.out.println("Mumy:"+ currentGroupID);

        System.out.println("Mumy:"+ currentGroupName);

        getModelReport();




//        total.setText(String.valueOf(adapter.getItemCount())); //just to show number
        return view;
    }

    public void getModelReport() {


        ArrayList<String> memberList = new ArrayList<>();
        AddTransaction add = new AddTransaction();
        FirebaseDatabase.getInstance().getReference("Groups")
                .child(currentGroupID).child(currentGroupName).child("Members")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> members = new ArrayList<>();
                        for (DataSnapshot snapshot4 : dataSnapshot.getChildren()) {
                            members.add(snapshot4.getValue().toString());
                            System.out.println("Mem: " + members);
                        }
                        memberList.addAll(members);
                        System.out.println("memList : " + memberList);
                        double a = memberList.size();
                        System.out.println("three: " + a);

                        for (int i = 1; i < a; i++) {
                            String user = memberList.get(i);


//            modelReports.add(new ModelReport(user1,tempAmt,user2));
//        modelReports.add(new ModelReport("A",1234, "B" ));
//        modelReports.add(new ModelReport("C",4567, "D"));
//        modelReports.add(new ModelReport("E",34589, "F"));


                            FirebaseDatabase.getInstance().getReference("TransactionUnequal")
                                    .child(currentGroupID)
                                    .child(currentEventID)
                                    .child(user)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            ArrayList<ModelReport> modelReport1 = new ArrayList<>();
                                            if (dataSnapshot.exists()) {
                                                Toast.makeText(getActivity(), "Exists", Toast.LENGTH_SHORT).show();

                                            }

                                            Map<String, Object> amountDetail = (Map<String, Object>) dataSnapshot.getValue();
                                            amountInvested = Double.parseDouble(Objects.requireNonNull(amountDetail).get("amountInvested").toString());
                                            amountToGet = Double.parseDouble(Objects.requireNonNull(amountDetail).get("amountToGet").toString());
                                            amountToPay = Double.parseDouble(Objects.requireNonNull(amountDetail).get("amountToPay").toString());
                                            if (amountToGet > 0) {
                                                tempAmt = amountToGet;
                                                user1 = user;
                                            } else {
                                                if (amountToPay > 0) {
                                                    tempAmt = amountToPay;
                                                    user2 = user;
                                                }

                                            }
                                            modelReport1.add(new ModelReport(user2, tempAmt, user1));

                                            System.out.println("temp : " + amountToGet);

                                            System.out.println("ModelReport : " + modelReport1.size());
//                        modelReport.addAll(modelReport1);

                                            RecyclerView recyclerView = view.findViewById(R.id.expenses_recycler_view);
                                            AdapterReport adapter = new AdapterReport(getActivity(), modelReport1);
                                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                            recyclerView.setAdapter(adapter);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                members=add.retrievePaidBy(currentGroupID,currentGroupName);


        }


    }



