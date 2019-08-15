package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddGroup extends AppCompatActivity {

    private static final String TAG = AddGroup.class.getSimpleName();
    ImageButton btn_back;
    ArrayList<Integer> groupFriend = new ArrayList<>();
    TextView save;
    EditText groupName;
    DatabaseReference dbReference;
    EditText friendName;
    ArrayList sb;
    StringBuffer sbb =null;
    String ID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupName = (EditText) findViewById(R.id.edit_text_group_name);
        friendName = (EditText) findViewById(R.id.edit_text_friend_name);
       // buttonAddFriend = (Button)findViewById(R.id.button_add_friend);
        dbReference = FirebaseDatabase.getInstance().getReference("Groups");
        ID = dbReference.push().getKey();
        HashMap<String, Object> totalFriends = new HashMap<String, Object>();

       // refId = randomAlphaNumeric(8);

        //For Spinner

//        ArrayAdapter<String> dataAdapter;
//        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, retrieve());
//        spinnerFriendName = (Spinner) findViewById(R.id.spinner_friend_name);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerFriendName.setAdapter(dataAdapter);
//
//        spinnerFriendName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = spinnerFriendName.getSelectedItem().toString();
//                if(parent.getItemAtPosition(position).equals("Choose Friend")){
//                    //do nothing
//                }
//                else{
//                    buttonAddFriend.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            totalFriends.put("1", item);
//                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        ArrayAdapter<String> dataAdapter;
//        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, retrieve());
//        spinnerFriendName = (Spinner) findViewById(R.id.spinner_friend_name);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerFriendName.setAdapter(dataAdapter);

//        spinnerFriendName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = spinnerFriendName.getSelectedItem().toString();
//                if(parent.getItemAtPosition(position).equals("Choose Friend")){
//                    //do nothing
//                }
//                else{
//                    totalFriends.put("true", item);
//                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        btn_back = findViewById(R.id.image_button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGroup.super.onBackPressed();
            }
        });

//        buttonAddFriend = (Button)findViewById(R.id.button_add_friend);
//        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String friend_name = friendName.getText().toString();
//                String group_name = groupName.getText().toString();
//                if(TextUtils.isEmpty(friend_name)){
//                    Toast.makeText(AddGroup.this, "Enter the friend name", Toast.LENGTH_SHORT).show();
//                }
//                dbReference.child(group_name).child("Members").setValue(friend_name).
//                        addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//                                    Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });


        save = (TextView)findViewById(R.id.textView_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group_name = groupName.getText().toString();
                if (TextUtils.isEmpty(group_name)) {
                    Toast.makeText(AddGroup.this, "Enter the group name", Toast.LENGTH_SHORT).show();
                }
                else {
                    //AddGroupInfo groupInfo = new AddGroupInfo(totalFriends);
                    dbReference.child(ID).child(groupName.getText().toString()).child("Members").setValue(sb).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        GroupInfo groupInfo = new GroupInfo(ID, groupName.getText().toString());
                                        FirebaseDatabase.getInstance().getReference("GroupName").child(ID).setValue(groupInfo);
                                        FirebaseDatabase.getInstance().getReference("GroupEvent").child(ID).child("Events").setValue("");
                                        Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent i = new Intent(AddGroup.this, Group.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }, 1500);
                                    } else {
                                        Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
         });



        //my part_1

//        buttonAddFriend = findViewById(R.id.button_add_friend);
//        friendSelected = findViewById(R.id.tv_friend_selected);
//
//        // convert arraylist to string[] array;
//        Object[] objectList = retrieve().toArray();
//        friendList = Arrays.copyOf(objectList,objectList.length,String[].class);

//        ArrayList<String> ar = retrieve();
//        friendList= new String[ar.size()];
//        for(int j = 0; j < ar.size(); j++){
//            friendList[j] = ar.get(j);
//        }

//        checkedFriend = new boolean[friendList.length];
//
//        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final  AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddGroup.this);
//                mBuilder.setTitle("Your Friends");
//                mBuilder.setMultiChoiceItems(friendList, checkedFriend, new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                                if(isChecked){
//                                        groupFriend.add(position);
//                                    } else {
//                                        groupFriend.remove((Integer.valueOf(position)));
//                                    }
//                                }
//                });
//                        mBuilder.setCancelable(false);
//                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int position) {
//                                String item = "";
//                                for (int i=0; i<groupFriend.size();i++){
//                                    item = item + friendList[groupFriend.get(i)];
//                                    if (i != groupFriend.size()-1){
//                                        item = item + ",";
//                                    }
//                                }
//                                friendSelected.setText(item);
//                            }
//                        });
//
//                        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int position) {
//                                dialogInterface.dismiss();
//                            }
//                        });
//
//                        mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int position) {
//                                for(int i = 0; i < checkedFriend.length;i++){
//                                    checkedFriend[i] = false;
//                                    groupFriend.clear();
//                                    friendSelected.setText("");
//                                }
//                            }
//                        });
//
//                        AlertDialog mDialog = mBuilder.create();
//                        mDialog.show();
//            }
//        });
//

        // end of my part_1

        //my part_2

        ArrayList<ModelAddGroup> friendLists= retrieve();
        RecyclerView recyclerView = findViewById(R.id.rv_add_friend);
        AdapterAddGroup adapterAddGroup = new AdapterAddGroup(this,friendLists);
        recyclerView.setAdapter(adapterAddGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab_add_friend = findViewById(R.id.fab_addfriend);
        fab_add_friend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sb = new ArrayList();
                sbb= new StringBuffer();

                for(ModelAddGroup m : adapterAddGroup.checkedFriends){
                    sb.add(m.getusername());
                    sbb.append(m.getusername());
                    sbb.append("\n");
                }

                if(adapterAddGroup.checkedFriends.size()>0){
                    Toast.makeText(AddGroup.this,sbb.toString(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddGroup.this,"Please select friends",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // end my part_2
    }


    public ArrayList<ModelAddGroup> retrieve(){
        ArrayList<ModelAddGroup> friendLists = new ArrayList<>();
        friendLists.clear();
//        friendLists.add(0, "Choose Friend");
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String name = (String) Objects.requireNonNull(data).get("username");
                    friendLists.add(new ModelAddGroup(name, false));
                }
                System.out.println(friendLists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return friendLists;
    }

    /*
    generate random value
     */
//    public String randomAlphaNumeric(int count) {
//        StringBuilder builder = new StringBuilder();
//        while (count-- != 0) {
//            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
//            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
//        }
//        return builder.toString();
//    }
}