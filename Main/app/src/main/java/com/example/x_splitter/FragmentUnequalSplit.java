package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FragmentUnequalSplit extends DialogFragment {
    public FragmentUnequalSplit() {
    }

    Context context;
    View view;
    FirebaseDatabase dbReference;
    ArrayList<String> paidByListTransaction;
    ArrayList<String> UnequalTransaction;
    List<String> groupMembers;
    String GroupId;
    String GroupName;
    String EventId;
    double size;
    ArrayList<ModelUnequalSplit> memberData;
    double totalSum;
    double amtReceive;
    double amountInvested;
    double amountToGet;
    double amountToPay;




    public FragmentUnequalSplit(String groupId, String groupname, String eventId) {
        GroupId = groupId;
        GroupName = groupname;
        EventId = eventId;

    }



    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.unequal_split_popup, container, false);

//        EditText memberShare = view.findViewById(R.id.unequal_split_member_share);
//        int memShare = Integer.parseInt(memberShare.getText().toString());
        memberData = getMemberData(GroupId, GroupName, EventId);
        TextView btn_ok = view.findViewById(R.id.btn_ok_unequal_split);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel_unequal_split);
        Intent intent = getActivity().getIntent();
//        String aR = intent.getStringExtra("AmountReceived");

//


        RecyclerView recyclerView = view.findViewById(R.id.rv_split_unequal);
        AdapterUnequalSplit adapterGroup = new AdapterUnequalSplit(this.context, memberData);
        recyclerView.setAdapter(adapterGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));


        TextView totalCompare = view.findViewById(R.id.tv_total_unequal_split);

        btn_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getActivity().getIntent().getExtras();
                String aR =getArguments().getString("params");
                amtReceive= Double.parseDouble(aR);
                HashMap<String,String> hashMap1 = adapterGroup.hashMap;
                for (Map.Entry map  :  hashMap1.entrySet())
                {
                    totalSum +=  Double.parseDouble((String) map.getValue());
                }
                System.out.println("Total "+totalSum);

                totalCompare.setText(Double.toString(totalSum));//print totalsum in fragment
                if(amtReceive == totalSum)
                {
                        //String display huncha but totalSum chahi display vayena
                    for (Map.Entry map  :  hashMap1.entrySet())
                    {
                        String name = (String) map.getKey();
                        double value =Double.parseDouble((String) map.getValue());
                        transactionData(GroupId,GroupName,EventId,name,value);
                    }

                    Intent i = new Intent(getActivity(),AddTransaction.class);
                    startActivity(i);

                }
                else
                {
                    System.out.println("Try Again");
                    Toast.makeText(getActivity(), "Share amount mismatch with total amount",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddTransaction.class);
                startActivity(i);
            }
        });

//        System.out.println("check "+ adapterGroup.hashMap);

        this.getDialog().setTitle("Enter shares");
        return view;

    }

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getActivity().getIntent().getExtras();
//            String aR =getArguments().getString("params");
//            System.out.println("Ruxali"+ aR);
//
//    }

    public ArrayList<ModelUnequalSplit> transactionData(String groupId, String gname, String eventId, String name, double value){
        ArrayList<ModelUnequalSplit> modelUnequalSplits1 = new ArrayList<>();
        modelUnequalSplits1.clear();

        FirebaseDatabase.getInstance().getReference("TransactionUnequal").child(groupId).child(eventId).child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> amountDetail = (Map<String, Object>) dataSnapshot.getValue();
                amountInvested = Double.parseDouble( Objects.requireNonNull(amountDetail).get("amountInvested").toString());
                amountToGet = Double.parseDouble(Objects.requireNonNull(amountDetail).get("amountToGet").toString());
                amountToPay = Double.parseDouble(Objects.requireNonNull(amountDetail).get("amountToPay").toString());
                System.out.println("AI"+amountInvested);
                System.out.println("ATP"+amountToPay);
                System.out.println("ATG"+amountToGet);
                double tempAmt=value;
                double difference = amountToGet-tempAmt;
                if(difference>=0) {
                    amountToGet = difference;
                    if (amountToPay != 0) {
                        if (amountToGet < amountToPay) {
                            amountToPay = amountToPay - amountToGet;
                            amountToGet = 0;
                        } else {
                            amountToGet = amountToGet - amountToPay;
                            amountToPay = 0;
                        }
                    }
                }
                    else {
                        amountToPay=amountToPay-difference;
                        amountToGet=0;
                    }


                Map<String,Object> amountDetails = new HashMap<>();
//                amountDetails.put("amountInvested",amountInvested);
                amountDetails.put("amountToGet",amountToGet);
                amountDetails.put("amountToPay",amountToPay);
                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
                        .child(groupId)
                        .child(eventId)
                        .child(name)
                        .updateChildren(amountDetails);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

return modelUnequalSplits1;
    }

    public ArrayList<ModelUnequalSplit> getMemberData(String id, String gname, String eventId) {
        ArrayList<ModelUnequalSplit> modelUnequalSplits = new ArrayList<>();
        modelUnequalSplits.clear();
        paidByListTransaction = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("Groups").child(id).child(gname).child("Members")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        groupMembers = new ArrayList<>();
                        for(DataSnapshot snapshot4 : dataSnapshot.getChildren()){
                            groupMembers.add(snapshot4.getValue().toString());
                        }
                        paidByListTransaction.addAll(groupMembers);
//                        for(int i=0; i<groupMembers.size(); i++){
//                            System.out.println("Namkong" + groupMembers.get(i));
//                        }
                        for(int i=0; i< groupMembers.size();i++){
                            modelUnequalSplits.add(new ModelUnequalSplit(groupMembers.get(i), ""));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return modelUnequalSplits;

    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_ok_unequal_split:
//
//                break;
//
//            case R.id.btn_cancel_unequal_split:
//                //saveTransaction();
//                break;
//        }
//    }
}
