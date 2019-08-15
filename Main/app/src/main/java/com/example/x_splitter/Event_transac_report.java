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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Event_transac_report extends AppCompatActivity {

    FloatingActionButton fab_add;
    Button btn_settle;
    TextView CurrentEventName;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_transac_report);

        CurrentEventName = findViewById(R.id.event_name);

        Intent intent = getIntent();
        String currentEventName = intent.getStringExtra("currentEventName");
        String currentEventID = intent.getStringExtra("currentEventID");
        String currentGroupID = intent.getStringExtra("currentGroupID");
        System.out.println("currentEventName" + currentEventName);
        System.out.println("currentEventID" + currentEventID);
        System.out.println("currentGroupID" + currentGroupID);
//        Intent intent2 = getIntent();
//        String GroupID = intent2.getStringExtra("GroupID");
//        String EventID = intent2.getStringExtra("EventID");
//        System.out.println("Uncle:"+GroupID);
//        System.out.println("Uncle:"+EventID);
        CurrentEventName.setText(currentEventName);


//        String currentEventID = intent.getStringExtra("currentEventID");
//        String currentGroupName = intent.getStringExtra("currentGroupName");
//        String currentGroupID = intent.getStringExtra("currentGroupID");


//        btn_settle=findViewById(R.id.btn_settle);
//        btn_settle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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
