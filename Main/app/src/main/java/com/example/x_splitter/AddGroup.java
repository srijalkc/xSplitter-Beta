package com.example.x_splitter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGroup extends AppCompatActivity {

    ImageButton btn_back;
    Button buttonAddFriend;
    TextView save;
    EditText groupName;
    DatabaseReference dbReference;
    //Integer childCount;
    EditText friendName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_add_group);
        //childCount = linearLayout.getChildCount();
        //ArrayList<String> al = new ArrayList<>();

        groupName = (EditText) findViewById(R.id.edit_text_group_name);
        friendName = (EditText) findViewById(R.id.edit_text_friend_name);
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



//                LayoutInflater inflater = getLayoutInflater();
//                View view = inflater.inflate(R.layout.activity_add_group_inflater, linearLayout, false);
//                linearLayout.addView(view);
//                childCount++;
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

//            for (int i = 0; i < childCount; i++) {
//                View view = linearLayout.getChildAt(i);
//                if(view instanceof EditText){
//                    EditText editText = (EditText)view;
//                    String a = editText.getText().toString();
//                    al.add(a);
//                }
//            }

            //AddGroupInfo addGroupInfo = new AddGroupInfo();

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

}