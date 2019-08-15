package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddEvent extends AppCompatActivity {
    EditText eventName;
    TextView save;
    DatabaseReference dbReferenceEvent;
    ArrayList arEvent;
    String groupID;
    String groupname;
    ModelAddEvent model;
    String s;
    Spinner SpinnerAddEvent;
    ArrayList<String> GroupList;
    int size;
    String groupSelected;
    ArrayList<String> GroupLists;
    List<String> groupMembers;
    String name;
    String gid;
    double amountToPay = 0;
    double amountToGet = 0;
    double amountInvested = 0;
    ArrayList<String> paidByListTransaction;
    String itemPaidBy;
    String ID,grpId, Names;


    ImageButton btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        model = new ModelAddEvent();

        eventName = (EditText) findViewById(R.id.edit_text_event_name);

        dbReferenceEvent = FirebaseDatabase.getInstance().getReference("Events");
        String ID = dbReferenceEvent.push().getKey();

        btn_back = findViewById(R.id.image_button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEvent.super.onBackPressed();
            }
        });
        SpinnerAddEvent = findViewById(R.id.spinner_add_event);

        retrieveEvent();

        save = (TextView) findViewById(R.id.textView_save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveEvent(ID,grpId);
            }
        });
    }

   public void saveEvent(String ID, String grpID){
        String event_name = eventName.getText().toString();
        //EventInfo arEvent = new EventInfo(gid);
        if (TextUtils.isEmpty(event_name)) {
            Toast.makeText(AddEvent.this, "Please enter Event name", Toast.LENGTH_SHORT).show();
        } else {

            dbReferenceEvent.child(ID).child(eventName.getText().toString()).child("GroupID").setValue(grpID).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                EventInfo eventInfo = new EventInfo(ID, event_name, grpID);
                                FirebaseDatabase.getInstance().getReference("EventName").child(ID).setValue(eventInfo);
                                Toast.makeText(AddEvent.this, "Event Created", Toast.LENGTH_SHORT).show();
//                                ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("EID");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(AddEvent.this, Home.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(AddEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            TransactionInfo transactionInfo3 = new TransactionInfo(amountToPay, amountToGet, amountInvested);
            for(int i = 1; i < paidByListTransaction.size(); i++) {
                String j = paidByListTransaction.get(i);
                FirebaseDatabase.getInstance().getReference("TransactionUnequal").child(grpID).child(ID).child(j).setValue(transactionInfo3);
            }
        }
    }

    public ArrayList<String> retrieveEvent(){
        GroupLists = new ArrayList<>();
        GroupLists.clear();
        GroupLists.add(0, "Choose Group");
        FirebaseDatabase.getInstance().getReference("GroupName")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        groupMembers = new ArrayList<>();
                        List<GroupInfo> groupInfos = new ArrayList<>();
                        for(DataSnapshot snapshot1: dataSnapshot.getChildren()){
                            GroupInfo info = snapshot1.getValue(GroupInfo.class);
                            groupInfos.add(info);
                        }
                        List<String> gId = new ArrayList<>();
                        List<String> Name = new ArrayList<>();

                        for (int i = 0; i < groupInfos.size(); i++) {
                            gId.add(groupInfos.get(i).ID);
                            Name.add(groupInfos.get(i).GroupName);

                        }
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                            name = (String) Objects.requireNonNull(data).get("GroupName");
                            gid = (String) Objects.requireNonNull(data).get("ID");
                            groupMembers.add(name);
                        }

                        GroupLists.addAll(groupMembers);
                        size = groupMembers.size();
                        for(int i = 1; i <= size; i++){
                            System.out.println("Srijal: "+ GroupLists.get(i));
                        }
                        ArrayAdapter<String> dataAdapterAddEvent = new ArrayAdapter<String>
                                (getBaseContext(), android.R.layout.simple_spinner_item, groupMembers);
                        dataAdapterAddEvent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpinnerAddEvent.setAdapter(dataAdapterAddEvent);
                        SpinnerAddEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {
                                groupSelected = SpinnerAddEvent.getSelectedItem().toString();
                                if (parent.getItemAtPosition(position).equals("Choose Group")) {
                                    //do Nothing
                                } else {
                                    grpId = gId.get(position);
                                    Names = Name.get(position);
                                    GroupList = new ArrayList();
                                    GroupList.add(groupSelected);
                                    retrievePaidBy(grpId, Names);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        return GroupLists;
    }

    public ArrayList<String> retrievePaidBy(String id,String name){
        paidByListTransaction = new ArrayList<>();
        paidByListTransaction.clear();
        paidByListTransaction.add(0, "Choose User");
        FirebaseDatabase.getInstance().getReference("Groups")
                .child(id).child(name).child("Members")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        groupMembers = new ArrayList<>();
                        for(DataSnapshot snapshot4 : dataSnapshot.getChildren()){
                            groupMembers.add(snapshot4.getValue().toString());
                        }
                        paidByListTransaction.addAll(groupMembers);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        return paidByListTransaction;
    }
}
