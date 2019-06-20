package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Event extends AppCompatActivity {
    FloatingActionButton fab_add;

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

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        AdapterHomeEvent adapterHomeEvent = new AdapterHomeEvent(this,getEventData());
        recyclerView.setAdapter(adapterHomeEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setBottomNavigationView();
    }

    public static ArrayList<ModelHomeEvent> getEventData(){
        ArrayList<ModelHomeEvent> modelHomeEvents = new ArrayList<>();

        modelHomeEvents.add(new ModelHomeEvent("Tour", "Settled","123.0","12.0"));
        modelHomeEvents.add(new ModelHomeEvent("Birthday", "Not Settled", "234.0","123.7"));
        modelHomeEvents.add(new ModelHomeEvent("Party", "Not Settled", "568.9","67890.0"));
        modelHomeEvents.add(new ModelHomeEvent("Tour", "Settled","123.0","12.0"));
        modelHomeEvents.add(new ModelHomeEvent("Birthday", "Not Settled", "234.0","123.7"));
        modelHomeEvents.add(new ModelHomeEvent("Party", "Not Settled", "568.9","67890.0"));

        return modelHomeEvents;
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        BottomNavigationHelper.enableNavigation(Event.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}