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

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {
    TextView TextViewSave;
    EditText TextViewAmount;
    TextView TextViewDate;
    EditText TextViewCategory;
    EditText TextViewPaidBy;
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


    DatabaseReference databaseTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        btn_back = findViewById(R.id.image_button_back);
        SpinnerPaidBy =  findViewById(R.id.spinner_paidby);
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.splits, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_split.setAdapter(adapter);
        spinner_split.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Splitting")){
                    //do Nothing
                }
                else if(parent.getItemAtPosition(position).equals("Split Equally")){

                    Toast.makeText(AddTransaction.this,"Split Equally",Toast.LENGTH_SHORT).show();
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
//***********************End of Date & Time*********************************************************

    private void saveTransaction(){
        String amount = TextViewAmount.getText().toString().trim();
        String date = TextViewDate.getText().toString().trim();
        //String event = TextViewEvent.getText().toString().trim();
        String category = TextViewCategory.getText().toString().trim();
        String paidBy = TextViewPaidBy.getText().toString().trim();

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

//*********************** Group with Spinner****************************************************
    public ArrayList<String> retrievegroup(){
        ArrayList<String> groupListTranscation = new ArrayList<>();

        groupListTranscation.clear();
        groupListTranscation.add(0, "Choose Group");
        FirebaseDatabase.getInstance().getReference("GroupName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){
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
    public ArrayList<String> retrieveEvent(String id){

        ArrayList<String> eventListTransaction = new ArrayList<>();
        eventListTransaction.clear();
        eventListTransaction.add(0, "Choose Event");
        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<EventInfo> eventInfos  = new ArrayList<>();

                for(DataSnapshot snapshot3 : dataSnapshot.getChildren()){
                    System.out.println("SnapShot : "+snapshot3.getValue().toString());
                    EventInfo eventinfo = snapshot3.getValue(EventInfo.class);
                    eventInfos.add(eventinfo);
                }

                runOnUiThread(() -> {
                    List<String> eId = new ArrayList<>();
                    List<String> eventname = new ArrayList<>();
                    List<String> grpId = new ArrayList<>();
                    for (int i = 0; i < eventInfos.size(); i++) {
                        //System.out.println("ID : " + eventInfos.get(i).GroupID.toString());
                                if (eventInfos.get(i).GroupID.equals(id)){
                                    eId.add(eventInfos.get(i).ID);
                                    grpId.add(eventInfos.get(i).GroupID);
                                    eventname.add(eventInfos.get(i).EventName);
                                }
                    }

                ArrayAdapter<String> dataAdapterEvent= new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, eventname);
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
    public ArrayList<String> retrievePaidBy(String id,String name){
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
//***********************End of PaidBy**********************************************************