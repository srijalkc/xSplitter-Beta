package com.example.x_splitter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

    ImageButton btn_back;
    Button buttonAddFriend;
    TextView friendSelected;
    String[] friendList;
    boolean[] checkedFriend;
    ArrayList<Integer> groupFriend = new ArrayList<>();
    TextView save;
    EditText groupName;
    DatabaseReference dbReference;
    //Integer childCount;
    EditText friendName;
    Spinner spinnerFriendName;
    RecyclerView recyclerView;
    //RecyclerView.Adapter adapter;
    //List<ListItem> listItems;
    FirebaseRecyclerAdapter firebaseAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupName = (EditText) findViewById(R.id.edit_text_group_name);
        friendName = (EditText) findViewById(R.id.edit_text_friend_name);
       // buttonAddFriend = (Button)findViewById(R.id.button_add_friend);
        dbReference = FirebaseDatabase.getInstance().getReference("Groups");
        String ID = dbReference.push().getKey();
        HashMap<String, Object> totalFriends = new HashMap<String, Object>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_addfriends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //listItems = new ArrayList<>();
        //adapter = new AddGroupAdapter(listItems, this);
        //recyclerView.setAdapter(adapter);


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
                    AddGroupInfo groupInfo = new AddGroupInfo(totalFriends);
                    dbReference.child(ID).child(groupName.getText().toString()).child("Members").setValue(totalFriends).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
         });



        //my part_1
//
//        buttonAddFriend = findViewById(R.id.button_add_friend);
//        friendSelected = findViewById(R.id.tv_friend_selected);
//
//        to convert arraylist to string[] array;
//        Object[] objectList = retrieve().toArray();
//        friendList = Arrays.copyOf(objectList,objectList.length,String[].class);
//
//        CharSequence[] cs = retrieve().toArray(new CharSequence[retrieve().size()]);
//
//        checkedFriend = new boolean[friendList.length];
//
//        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final  AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddGroup.this);
//                mBuilder.setTitle("Your Friends");
//                mBuilder.setMultiChoiceItems(cs, checkedFriend, new DialogInterface.OnMultiChoiceClickListener() {
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

        // end of my part_1

    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<ListItem> options = new FirebaseRecyclerOptions.Builder<ListItem>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), ListItem.class)
                .build();

        FirebaseRecyclerAdapter<ListItem, AddGroupViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListItem, AddGroupViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AddGroupViewHolder addGroupViewHolder, int i, @NonNull ListItem listItem) {
                addGroupViewHolder.User.setText(listItem.getemail());
            }

            @NonNull
            @Override
            public AddGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardview_add_group, parent, false);
                AddGroupViewHolder ViewHolder = new AddGroupViewHolder(v);
                return ViewHolder;

            }
        };

    }

    public static class AddGroupViewHolder extends RecyclerView.ViewHolder{
        TextView User;

        public AddGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            User = itemView.findViewById(R.id.ttextview_addFriend);
        }

    }

    public ArrayList<String> retrieve(){
        ArrayList<String> friendLists = new ArrayList<>();
        friendLists.add(0, "Choose Friend");
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String name = (String) Objects.requireNonNull(data).get("email");
                    friendLists.add(name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return friendLists;
    }
}