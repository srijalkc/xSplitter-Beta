package com.example.x_splitter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class AddGroup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageButton btn_back;
    Button buttonAddFriend;
    TextView save;
    EditText groupName;
    DatabaseReference dbReference;
    //Integer childCount;
    EditText friendName;
    Spinner spinnerFriendName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_add_group);
        //childCount = linearLayout.getChildCount();
        //ArrayList<String> al = new ArrayList<>();

        groupName = (EditText) findViewById(R.id.edit_text_group_name);
        friendName = (EditText) findViewById(R.id.edit_text_friend_name);

        //For Spinner
        spinnerFriendName = (Spinner) findViewById(R.id.spinner_friend_name);
        spinnerFriendName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, retrieve()));

        dbReference = FirebaseDatabase.getInstance().getReference("Groups");

        //String friend_name = friendName.getText().toString();

        btn_back = findViewById(R.id.image_button_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGroup.super.onBackPressed();
            }
        });


        buttonAddFriend = (Button)findViewById(R.id.button_add);
        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friend_name = friendName.getText().toString();
                String group_name = groupName.getText().toString();
                if(TextUtils.isEmpty(friend_name)){
                    Toast.makeText(AddGroup.this, "Enter the friend name", Toast.LENGTH_SHORT).show();
                }
                dbReference.child(group_name).child("Members").setValue(friend_name).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        save = (TextView)findViewById(R.id.textView_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String group_name = groupName.getText().toString();
            if(TextUtils.isEmpty(group_name)){
                Toast.makeText(AddGroup.this, "Enter the group name", Toast.LENGTH_SHORT).show();
            }

            dbReference.child("Groups").child(group_name).setValue("").
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddGroup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
});

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "text", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public ArrayList<String> retrieve(){
        ArrayList<String> friendLists = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String name = (String) Objects.requireNonNull(data).get("username");
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