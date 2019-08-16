package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Event_transac_report extends AppCompatActivity {

    FloatingActionButton fab_add;
    Button btn_settle;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView CurrentEventName;
    Double amountInvested;
    Double amountToGet;
    Double amountToPay;
    Double tempAmt;
    String user1;
    String user2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_transac_report);

        CurrentEventName = findViewById(R.id.event_name);

        Intent intent = getIntent();
        String currentEventName = intent.getStringExtra("currentEventName");
//        Intent intent2 = getIntent();
//        String GroupID = intent2.getStringExtra("GroupID");
//        String EventID = intent2.getStringExtra("EventID");
//        System.out.println("Uncle:"+GroupID);
//        System.out.println("Uncle:"+EventID);
        CurrentEventName.setText(currentEventName);


//        String currentEventID = intent.getStringExtra("currentEventID");
//       String currentGroupName = intent.getStringExtra("currentGroupName");
//        String currentGroupID = intent.getStringExtra("currentGroupID");

//        System.out.println("GID" + currentGroupID);
//        System.out.println("EID" + currentEventID);



//                FragmentTransaction fr= getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.,)

        String currentEventID = intent.getStringExtra("currentEventID");
        String currentGroupID = intent.getStringExtra("currentGroupID");

        FirebaseDatabase.getInstance().getReference("Transactions")
                .child(currentGroupID)
                .child(currentEventID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                            String amount = (String) Objects.requireNonNull(data).get("amount");
                            String category = (String) Objects.requireNonNull(data).get("category");
                            String date = (String) Objects.requireNonNull(data).get("date");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



//                Intent ij = new Intent(Event_transac_report.this,FragmentReport.class);
//                ij.putExtra("user1",user1);
//                ij.putExtra("tempAmt",tempAmt);
//                ij.putExtra("user2",user2);
//                startActivity(ij);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Event_transac_report.this,FAB_Menu_page.class));
            }
        });

        tabLayout = findViewById(R.id.tab_transac_report);
        viewPager = findViewById(R.id.view_pager_transac_report);

        AdapterEvent_transac_report adapter = new AdapterEvent_transac_report(getSupportFragmentManager());

        adapter.AddFragment(new FragmentTransac(),"Transactions");
        adapter.AddFragment(new FragmentReport(),"Report");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setBottomNavigationView();
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(Event_transac_report.this, Home.class); // Activity_num = 0
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(Event_transac_report.this, Group.class); // Activity_num = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_event:
                        Intent intent3 = new Intent(Event_transac_report.this, Event.class); // Activity_num = 3
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(Event_transac_report.this, Profile.class); // Activity_num = 4
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;

                }
                return false;
            }
        });
    }
}
