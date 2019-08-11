package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Event extends AppCompatActivity {
    FloatingActionButton fab_add;
    static String GN;
    static String ID;
    private static final int Activity_num = 3; // for recognizing menu item number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Event.this,FAB_Menu_page.class));
            }
        });

        ArrayList<ModelHomeEvent> event = getEventData();


        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        AdapterHomeEvent adapterHomeEvent = new AdapterHomeEvent(this,event);
        recyclerView.setAdapter(adapterHomeEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setBottomNavigationView();
    }

    public static ArrayList<ModelHomeEvent> getEventData(){
        ArrayList<ModelHomeEvent> modelHomeEvents = new ArrayList<>();
        modelHomeEvents.clear();

        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> eventdata = (Map<String, Object>) snapshot.getValue();
                    String eventname = (String) Objects.requireNonNull(eventdata).get("EventName");
                    ID = (String) Objects.requireNonNull(eventdata).get("GroupID");
                    FirebaseDatabase.getInstance().getReference("GroupName").child(ID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Map<String, Object> groupdata = (Map<String, Object>) dataSnapshot.getValue();
                                System.out.println(groupdata);
                                GN = (String) Objects.requireNonNull(groupdata).get("GroupName");
                                System.out.println(GN);
                            modelHomeEvents.add(new ModelHomeEvent(eventname, GN, "Not Settled", "123.0", "12.0"));



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
//        modelHomeEvents.add(new ModelHomeEvent("Tour", "Kathford","Not Settled","123.0","12.0"));
//        modelHomeEvents.add(new ModelHomeEvent("Birthday","Gang", "Not Settled", "234.0","123.7"));
//        modelHomeEvents.add(new ModelHomeEvent("Party", "Lolwa","Not Settled", "568.9","67890.0"));
//        modelHomeEvents.add(new ModelHomeEvent("Tour", "Kathford","Settled","123.0","12.0"));
//        modelHomeEvents.add(new ModelHomeEvent("Birthday","Adhikari", "Not Settled", "234.0","123.7"));
//        modelHomeEvents.add(new ModelHomeEvent("Party", "Lolwa","Not Settled", "568.9","67890.0"));

        return modelHomeEvents;
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(Event.this, Home.class); // Activity_num = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(Event.this, Group.class); // Activity_num = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_event:
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(Event.this, Profile.class); // Activity_num = 4
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;

                }
                return false;
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}