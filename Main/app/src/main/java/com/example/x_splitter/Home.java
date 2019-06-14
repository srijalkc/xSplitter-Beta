package com.example.x_splitter;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
 private static final int Activity_num = 0; // for recognizing menu item number
 private ArrayList<String> mGroupNames = new ArrayList<>();
 private ArrayList<String> mImageUrls = new ArrayList<>();
 FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,FAB_Menu_page.class));
            }
        });

        setBottomNavigationView();
        getImages();

    }

    private void getImages(){

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mGroupNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mGroupNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mGroupNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mGroupNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mGroupNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mGroupNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mGroupNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mGroupNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mGroupNames.add("Washington");

        initRecyclerView();

    }

    private void initRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.group_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        RecycleViewAdapterHomeGroup adapter = new RecycleViewAdapterHomeGroup(this, mGroupNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }


    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        BottomNavigationHelper.enableNavigation(Home.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }

}
