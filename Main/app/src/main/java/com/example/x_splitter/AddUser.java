package com.example.x_splitter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AddUser extends AppCompatActivity {

    EditText SelectUserName;
    ImageButton UserSearch;
    RecyclerView UserList;
    DatabaseReference dbRef;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        dbRef = FirebaseDatabase.getInstance().getReference("Users");

        btn_back = findViewById(R.id.image_button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddUser.super.onBackPressed();
            }
        });

        SelectUserName = findViewById(R.id.text_select_uname);
        UserSearch =  findViewById(R.id.image_view_search);

        UserList =  findViewById(R.id.rv_add_user);
        UserList.setHasFixedSize(true);
        UserList.setLayoutManager(new LinearLayoutManager(this));



        UserSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchUser = SelectUserName.getText().toString();
                searchUsers(searchUser);
                firebaseRecyclerAdapter.startListening();
            }
        });

    }
    private void searchUsers(String user){
        Query searchUserQuery = dbRef.orderByChild("username").startAt(user).endAt(user + "\uf8ff");

//        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter() {
//        }

        FirebaseRecyclerOptions<AddUserModel> options = new FirebaseRecyclerOptions.Builder<AddUserModel>().setQuery(searchUserQuery, new SnapshotParser<AddUserModel>() {
            @NonNull
            @Override
            public AddUserModel parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new AddUserModel(snapshot.child("email").getValue().toString(),snapshot.child("username").getValue().toString());
            }
        }).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AddUserModel, FindUsersViewHolder>(options) {
//                = new FirebaseRecyclerAdapter<AddUserModel, FindUsersViewHolder>(AddUserModel.class, R.layout.cardview_search_friend, FindUsersViewHolder.class,searchUserQuery) {
            @Override
            protected void onBindViewHolder(@NonNull FindUsersViewHolder findUsersViewHolder, int i, @NonNull AddUserModel addUserModel) {
                findUsersViewHolder.search_uname.setText(addUserModel.getUsername());
                findUsersViewHolder.search_uemail.setText(addUserModel.getEmail());
            }

            @NonNull
            @Override
            public FindUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewEvent = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_search_friend, null);
                return new FindUsersViewHolder(viewEvent);
            }
        };
        UserList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        firebaseRecyclerAdapter.stopListening();
    }

    public static class FindUsersViewHolder extends RecyclerView.ViewHolder{
        //View mView;
        TextView search_uname;
        TextView search_uemail;

        public FindUsersViewHolder(@NonNull View itemView) {
            super(itemView);
            //mView = itemView;
            search_uname = itemView.findViewById(R.id.search_uname);
            search_uemail = itemView.findViewById(R.id.search_email);
        }


    }
}