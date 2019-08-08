package com.example.x_splitter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Map;
import java.util.Objects;

public class AddEvent extends AppCompatActivity {
    EditText eventName;
    TextView save;
    DatabaseReference dbReferenceEvent;
    ArrayList arEvent;
    String groupID;
    String groupname;

    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

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

        ModelAddEvent model = new ModelAddEvent();
        String GGID = model.getID();

        save = (TextView) findViewById(R.id.textView_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event_name = eventName.getText().toString();
                if (TextUtils.isEmpty(event_name)) {
                    Toast.makeText(AddEvent.this, "Please enter Event name", Toast.LENGTH_SHORT).show();
                } else {
                    dbReferenceEvent.child(ID).child(eventName.getText().toString()).setValue(arEvent).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        EventInfo eventInfo = new EventInfo(ID, event_name, GGID);
                                        FirebaseDatabase.getInstance().getReference("EventName").child(ID).setValue(eventInfo);
                                        Toast.makeText(AddEvent.this, "Event Created", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AddEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        ArrayList<ModelAddEvent> groupLists = retrieveEvents();
        RecyclerView recyclerViewEvent = findViewById(R.id.rv_add_event);
        AdapterAddEvent adapterAddEvent = new AdapterAddEvent(this, groupLists);
        recyclerViewEvent.setAdapter(adapterAddEvent);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvent.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab_addGroup = findViewById(R.id.fab_addGroup);
        fab_addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arEvent = new ArrayList();
                StringBuffer sbEvent = new StringBuffer();

                for(ModelAddEvent me : adapterAddEvent.checkedGroups){
                    arEvent.add(me.getID());
                    sbEvent.append(me.getGroupName());
                    sbEvent.append("\n");
                }

                if(adapterAddEvent.checkedGroups.size()>0){
                    Toast.makeText(AddEvent.this,sbEvent.toString(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddEvent.this,sbEvent.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public  ArrayList<ModelAddEvent> retrieveEvents() {
        ArrayList<ModelAddEvent> groupList = new ArrayList<>();
        groupList.clear();
        FirebaseDatabase.getInstance().getReference("GroupName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String, Object> groupdata = (Map<String, Object>) snapshot.getValue();
                    groupname = (String) Objects.requireNonNull(groupdata).get("GroupName");
                    groupID = (String) Objects.requireNonNull(groupdata).get("ID");
                    groupList.add(new ModelAddEvent(groupname, false, groupID));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return groupList;
    }
}
