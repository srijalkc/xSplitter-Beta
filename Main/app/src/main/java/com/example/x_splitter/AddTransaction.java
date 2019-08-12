package com.example.x_splitter;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {
    TextView TextViewSave;
    EditText TextViewAmount;
    TextView TextViewDate;
    EditText TextViewCategory;
    Spinner SpinnerPaidBy;
    Spinner SpinnerGroup;
    Spinner SpinnerEvent;
    Spinner SpinnerSplit;
    ImageButton btn_back;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    ArrayList totalGroupTransaction;
    ArrayList totalEventTransaction;
    ArrayList totalPaidByTransaction;
    List<GroupInfo> groupInfos;
    String groupnametransaction;
    String eventnametransaction;
    String groupnameID;
    String eventnameID;
    String se;
    String su;
    String itemPaidBy;
    int amountToPay = 0;
    int amountToGet = 0;
    int amountInvested=0;
    String amountTotal;
    int size;
    int at;
    int memberSize;
    ArrayList<String> paidByListTransaction;
    List<String> groupMembers;
    int equallySplittedAmount;

    DatabaseReference databaseTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        btn_back = findViewById(R.id.image_button_back);
        SpinnerPaidBy = findViewById(R.id.spinner_paidby);
        SpinnerEvent = findViewById(R.id.spinner_event);
        totalGroupTransaction = new ArrayList();
        totalEventTransaction = new ArrayList();

        groupInfos = new ArrayList<>();

        databaseTransaction = FirebaseDatabase.getInstance().getReference("Transaction");
        TextViewAmount = (EditText) findViewById(R.id.text_view_amount);
        TextViewCategory = (EditText) findViewById(R.id.text_view_category);







        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTransaction.super.onBackPressed();
            }
        });

        retrievegroup();

        Spinner spinner_split = findViewById(R.id.spinner_split);
        se = " Split Equally";
        su = " Split Unequally";
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.splits, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_split.setAdapter(adapter);
        spinner_split.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Splitting")) {
                    //do Nothing
                } else if (parent.getItemAtPosition(position).equals("Split Equally")) {

                    Toast.makeText(AddTransaction.this, "Split Equally", Toast.LENGTH_SHORT).show();
                } else if (parent.getItemAtPosition(position).equals("Split Unequally")) {
                    final FragmentManager fr = getSupportFragmentManager();
                    final FragmentUnequalSplit fragmentUnequalSplit = new FragmentUnequalSplit();
                    fragmentUnequalSplit.show(fr,"Member");
                    amounttotal = TextViewAmount.getText().toString().trim();
                    int at = Integer.parseInt(amounttotal);
                }
                else if(parent.getItemAtPosition(position).equals("Split Equally")){
                    amountTotal=TextViewAmount.getText().toString().trim();
                    at = Integer.parseInt(amountTotal);
                    memberSize = size;
                    equallySplittedAmount = at/memberSize;





                    Toast.makeText(AddTransaction.this,"A: " + equallySplittedAmount,Toast.LENGTH_SHORT).show();
                }
                else if(parent.getItemAtPosition(position).equals("Split Unequally")){
                    Toast.makeText(AddTransaction.this,"Selected Unequally",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//***********************Date & Time***************************************************************
        TextViewDate = (TextView) findViewById(R.id.text_view_date);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                TextViewDate.setText(date);
            }
        };

        TextViewSave = (TextView) findViewById(R.id.textView_save);

        TextViewSave.setOnClickListener(this);
        TextViewDate.setOnClickListener(this);
    }

    private void dateSelector() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddTransaction.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
//***********************End of Date & Time*********************************************************

    private void saveTransaction() {
        String amount = TextViewAmount.getText().toString().trim();
        String date = TextViewDate.getText().toString().trim();
        String category = TextViewCategory.getText().toString().trim();

        if(amount.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (date.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please choose Date", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(event.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Please enter event", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (category.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter Category", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(note.isEmpty()){
//            Toast.makeText(getApplicationContext(), "No Note added", Toast.LENGTH_SHORT).show();
//        }

        String id = databaseTransaction.push().getKey();
        String id1 = FirebaseDatabase.getInstance().getReference("TransactionEvent").push().getKey();
        String id2 = FirebaseDatabase.getInstance().getReference("TransactionUnequal").push().getKey();
        //TransactionInfo transactionInfo = new TransactionInfo(amount, date, event, category, paidBy, note);
//        TransactionInfo transactionInfo = new TransactionInfo
//                (amount, date, category, groupnametransaction, eventnametransaction, itemPaidBy);
//        TransactionInfo transactionInfo1 = new TransactionInfo(itemPaidBy, amount);

    //   TransactionInfo transactionInfo3 = new TransactionInfo( equallySplittedAmount, itemPaidBy, amountToPay, amountToGet, amount);
        //databaseTransaction.child(id).setValue(transactionInfo);

//        FirebaseDatabase.getInstance().getReference("TransactionEvent")
//                .child(groupnameID).child(eventnameID).child(id1).setValue(transactionInfo1);
//        Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();


//        for(int i = 1; i <=memberSize; i++ ) {
//            String j = paidByListTransaction.get(i);
//            if (j == itemPaidBy) {
//                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
//                        .child(groupnameID).child(eventnameID).child(j).setValue(transactionInfo2);
//                Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();
//
//                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
//                        .child(groupnameID).child(eventnameID).child(j).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            Map<String, Object> maps = (Map<String, Object>)dataSnapshot.getValue();
//                            String atgTemp;
//                            String atpTemp;
//                            String aiTemp;
//                            atgTemp = (String) Objects.requireNonNull(maps).get("amountToGet");
//                            atpTemp = (String) Objects.requireNonNull(maps).get("amountToPay");
//                            aiTemp = (String) Objects.requireNonNull(maps).get("amountInvested");
//                            String amount2 = TextViewAmount.getText().toString().trim();
//                            TransactionInfo transactionInfo4 = new TransactionInfo(amount2);
//                            if(Integer.parseInt(aiTemp) > 0){
//                                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
//                                        .child(groupnameID).child(eventnameID).child(j).setValue(transactionInfo4);
//
//                            }
//                        }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//
//
//            }
//            else {
//                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
//                        .child(groupnameID).child(eventnameID).child(j).setValue(transactionInfo3);
//            }
//        }


        for(int i = 1; i <=memberSize; i++ ) {
            String j = paidByListTransaction.get(i);
            TransactionInfo transactionInfo2 = new TransactionInfo( amountInvested, amountToPay, amountToGet);
            FirebaseDatabase.getInstance().getReference("TransactionUnequal")
                    .child(groupnameID).child(eventnameID).child(j).setValue(transactionInfo2);

            if (j == itemPaidBy) {
               //  Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();

                Map<String, Object> amountDetail =new HashMap<>();


//                FirebaseDatabase.getInstance().getReference("TransactionUnequal").child(groupnameID).child(eventnameID).child(j).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Map<String, Object> amountDetail = (Map<String, Object>) dataSnapshot.getValue();
//                         amountInvested = (int) Objects.requireNonNull(amountDetail).get("amountInvested");
//                         amountToGet = (int) Objects.requireNonNull(amountDetail).get("amountToGet");
//                         amountToPay = (int) Objects.requireNonNull(amountDetail).get("amountToPay");
                        amountInvested=amountInvested+at;
                        amountToGet=amountToGet-equallySplittedAmount;
                      //  TransactionInfo transactionInfo4 = new TransactionInfo(amountInvested,amountToGet,amountToPay);
                       
                        amountDetail.put("amountInvested",amountInvested);
                        amountDetail.put("amountToGet",amountToGet);
//                        amountDetail.put("amountToPay",amountToPay);
                        FirebaseDatabase.getInstance().getReference("TransactionUnequal")
                                .child(groupnameID).child(eventnameID).child(j).updateChildren(amountDetail);


                    }




            else {
                Map<String, Object> amountDetail =new HashMap<>();
                amountToPay=equallySplittedAmount;
                amountDetail.put("amountToPay",amountToPay);
                FirebaseDatabase.getInstance().getReference("TransactionUnequal")
                        .child(groupnameID).child(eventnameID).child(j).updateChildren(amountDetail);
            }
        }







        AddTransaction.super.onBackPressed();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_date:
                dateSelector();
                break;

            case R.id.textView_save:
                saveTransaction();
                break;
        }

    }

    //*********************** Group with Spinner****************************************************
    public ArrayList<String> retrievegroup() {
        ArrayList<String> groupListTranscation = new ArrayList<>();

        groupListTranscation.clear();
        groupListTranscation.add(0, "Choose Group");
        FirebaseDatabase.getInstance().getReference("GroupName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    groupListTranscation.add(groupnametransaction);
                    GroupInfo info = snapshot1.getValue(GroupInfo.class);
                    groupInfos.add(info);
                }
                runOnUiThread(() -> {
                    List<String> gId = new ArrayList<>();
                    List<String> name = new ArrayList<>();

                    for (int i = 0; i < groupInfos.size(); i++) {
                        gId.add(groupInfos.get(i).ID);
                        name.add(groupInfos.get(i).GroupName);

                    }

                    SpinnerGroup = findViewById(R.id.spinner_group);
                    ArrayAdapter<String> dataAdapterGroup = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, name);
                    dataAdapterGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SpinnerGroup.setAdapter(dataAdapterGroup);
                    SpinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String itemGroup = SpinnerGroup.getSelectedItem().toString();
                            if (parent.getItemAtPosition(position).equals("Choose Group")) {
                                //do Nothing
                            } else {

                                groupnameID = gId.get(position);
                                groupnametransaction = name.get(position);
                                retrievePaidBy(groupnameID, groupnametransaction);
                                retrieveEvent(groupnameID);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return groupListTranscation;
    }
//***********************End of Group**********************************************************

    //***********************Event with Spinner****************************************************
    public ArrayList<String> retrieveEvent(String id) {

        ArrayList<String> eventListTransaction = new ArrayList<>();
        eventListTransaction.clear();
        eventListTransaction.add(0, "Choose Event");
        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<EventInfo> eventInfos = new ArrayList<>();

                for (DataSnapshot snapshot3 : dataSnapshot.getChildren()) {
                    System.out.println("SnapShot : " + snapshot3.getValue().toString());
                    EventInfo eventinfo = snapshot3.getValue(EventInfo.class);
                    eventInfos.add(eventinfo);
                }

                runOnUiThread(() -> {
                    List<String> eId = new ArrayList<>();
                    List<String> eventname = new ArrayList<>();
                    List<String> grpId = new ArrayList<>();
                    for (int i = 0; i < eventInfos.size(); i++) {
                        //System.out.println("ID : " + eventInfos.get(i).GroupID.toString());
                        if (eventInfos.get(i).GroupID.equals(id)) {
                            eId.add(eventInfos.get(i).ID);
                            grpId.add(eventInfos.get(i).GroupID);
                            eventname.add(eventInfos.get(i).EventName);
                        }
                    }

                    ArrayAdapter<String> dataAdapterEvent = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, eventname);
                    dataAdapterEvent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SpinnerEvent.setAdapter(dataAdapterEvent);
                    SpinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String itemEvent = SpinnerGroup.getSelectedItem().toString();
                            if (parent.getItemAtPosition(position).equals("Choose Event")) {
                                //do Nothing
                            } else {
                                eventnameID = eId.get(position);
                                eventnametransaction = eventname.get(position);

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return eventListTransaction;
    }
//***********************End of Event**********************************************************

    //***********************PaidBy with Spinner****************************************************
    public ArrayList<String> retrievePaidBy(String id, String name) {
        final ArrayList<String> paidByListTransaction = new ArrayList<>();
//***********************PaidBy with Spinner****************************************************
    public ArrayList<String> retrievePaidBy(String id,String name){
        paidByListTransaction = new ArrayList<>();
        paidByListTransaction.clear();
        paidByListTransaction.add(0, "Choose User");
        FirebaseDatabase.getInstance().getReference("Groups")
                .child(id).child(name).child("Members")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> groupMembers = new ArrayList<>();
                        for (DataSnapshot snapshot4 : dataSnapshot.getChildren()) {
                        groupMembers = new ArrayList<>();
                        for(DataSnapshot snapshot4 : dataSnapshot.getChildren()){
                            groupMembers.add(snapshot4.getValue().toString());
                        }

                        runOnUiThread(() -> {
                            paidByListTransaction.addAll(groupMembers);
                            size = groupMembers.size();
                            ArrayAdapter<String> dataAdapterPaidBy = new ArrayAdapter<String>
                                    (getBaseContext(), android.R.layout.simple_spinner_item, paidByListTransaction);
                            dataAdapterPaidBy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SpinnerPaidBy.setAdapter(dataAdapterPaidBy);
                            SpinnerPaidBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {
                                    itemPaidBy = SpinnerPaidBy.getSelectedItem().toString();
                                    if (parent.getItemAtPosition(position).equals("Choose User")) {
                                        //do Nothing
                                    } else {
                                        totalPaidByTransaction = new ArrayList();
                                        totalPaidByTransaction.add(itemPaidBy);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        return paidByListTransaction;
    }

//***********************End of PaidBy**********************************************************
}