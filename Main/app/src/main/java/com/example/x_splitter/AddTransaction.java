package com.example.x_splitter;

import android.app.DatePickerDialog;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {
    TextView TextViewSave;
    EditText TextViewAmount;
    TextView TextViewDate;
    EditText TextViewCategory;
    EditText TextViewPaidBy;
    Spinner SpinnerPaidBy;
    Spinner SpinnerGroup;
    Spinner SpinnerEvent;
    List<GroupInfo> groupInfos;
    Spinner SpinnerSplit;
//    EditText TextViewNote;
    ImageButton btn_back;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    ArrayList totalGroupTransaction;
    ArrayList totalEventTransaction;
    ArrayList totalPaidByTransaction;
    String groupnametransaction;
    String groupnameID;


    DatabaseReference databaseTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        btn_back = findViewById(R.id.image_button_back);
        SpinnerPaidBy =  findViewById(R.id.spinner_paidby);
        totalGroupTransaction = new ArrayList();
        totalEventTransaction = new ArrayList();

        groupInfos = new ArrayList<>();

        databaseTransaction = FirebaseDatabase.getInstance().getReference("Transaction");

        TextViewAmount = (EditText) findViewById(R.id.text_view_amount);

        TextViewCategory = (EditText) findViewById(R.id.text_view_category);


retrievegroup();

//*************************Group Spinner***********************************************

//***********************end of Group Spinner**********************************************


//***********************Event Spinner*****************************************************
        SpinnerEvent = findViewById(R.id.spinner_event);
        ArrayAdapter<String> dataAdapterEvent= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, retrieveEvent());
        dataAdapterEvent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerEvent.setAdapter(dataAdapterEvent);
        SpinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String itemEvent = SpinnerGroup.getSelectedItem().toString();
                if(parent.getItemAtPosition(position).equals("Choose Event")){
                    //do Nothing
                }
                else{
//                    totalEventTransaction.add(itemEvent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//***********************end of Event Spinner****************************************************


//***********************PaidBy Spinner****************************************************


//***********************end of PaidBy Spinner****************************************************


        Spinner spinner_split = findViewById(R.id.spinner_split);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.splits, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_split.setAdapter(adapter);


        TextViewDate = (TextView) findViewById(R.id.text_view_date);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTransaction.super.onBackPressed();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                TextViewDate.setText(date);
            }
        };

        TextViewSave = (TextView)findViewById(R.id.textView_save);

        TextViewSave.setOnClickListener(this);
        TextViewDate.setOnClickListener(this);
    }

    private void dateSelector(){
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

    private void saveTransaction(){
        String amount = TextViewAmount.getText().toString().trim();
        String date = TextViewDate.getText().toString().trim();
        //String event = TextViewEvent.getText().toString().trim();
        String category = TextViewCategory.getText().toString().trim();
        String paidBy = TextViewPaidBy.getText().toString().trim();
        //String note = TextViewNote.getText().toString().trim();

        if(amount.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if(date.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please choose Date", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(event.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Please enter event", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if(category.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Category", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(note.isEmpty()){
//            Toast.makeText(getApplicationContext(), "No Note added", Toast.LENGTH_SHORT).show();
//        }

        String id = databaseTransaction.push().getKey();
        //TransactionInfo transactionInfo = new TransactionInfo(amount, date, event, category, paidBy, note);
        TransactionInfo transactionInfo = new TransactionInfo(amount, date, category, paidBy);
        databaseTransaction.child(id).setValue(transactionInfo);
        Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view_date:
                dateSelector();
                break;

            case R.id.textView_save:
                saveTransaction();
                break;
        }

    }

    public ArrayList<String> retrievegroup(){
        ArrayList<String> groupListTranscation = new ArrayList<>();
        groupListTranscation.clear();
        groupListTranscation.add(0, "Choose Group");
        FirebaseDatabase.getInstance().getReference("GroupName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){
                    Map<String, Object> groupdatatransaction = (Map<String, Object>) snapshot1.getValue();

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
                                groupnametransaction = name.get(position);
                                groupnameID = gId.get(position);
                                retrievePaidBy(groupnameID, groupnametransaction);
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

    public ArrayList<String> retrieveEvent(){
        ArrayList<String> eventListTransaction = new ArrayList<>();
        eventListTransaction.clear();
        eventListTransaction.add(0, "Choose Event");
        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot3 : dataSnapshot.getChildren()){
                    Map<String, Object> eventdatatransaction = (Map<String, Object>) snapshot3.getValue();
                    String eventnametransaction = (String) Objects.requireNonNull(eventdatatransaction).get("EventName");
                    eventListTransaction.add(eventnametransaction);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return eventListTransaction;
    }
    public ArrayList<String> retrievePaidBy(String id,String name){
//        GroupInfo ginfo = new GroupInfo();
//        groupnameID = ginfo.getID();
//        System.out.println("Id : "+groupnameID);
//        System.out.println("Name : "+groupnametransaction);

        final ArrayList<String> paidByListTransaction = new ArrayList<>();
        paidByListTransaction.clear();
        paidByListTransaction.add(0, "Choose User");
        FirebaseDatabase.getInstance().getReference("Groups")
                .child(id).child(name).child("Members")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> groupMembers = new ArrayList<>();
                        for(DataSnapshot snapshot4 : dataSnapshot.getChildren()){
//                            Map<String, Object> paidbydatatransaction = (Map<String, Object>) snapshot4.getValue();
                            groupMembers.add(snapshot4.getValue().toString());
                        }


                        runOnUiThread(() -> {
                            paidByListTransaction.addAll(groupMembers);
                            ArrayAdapter<String> dataAdapterPaidBy = new ArrayAdapter<String>
                                    (getBaseContext(), android.R.layout.simple_spinner_item, paidByListTransaction);
                            dataAdapterPaidBy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SpinnerPaidBy.setAdapter(dataAdapterPaidBy);
                            SpinnerPaidBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {
                                    String itemPaidBy = SpinnerPaidBy.getSelectedItem().toString();
                                    if(parent.getItemAtPosition(position).equals("Choose User")){
                                        //do Nothing
                                    }
                                    else{
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

}
