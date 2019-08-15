package com.example.x_splitter;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
 private static final int Activity_num = 0; // for recognizing menu item number
// private ArrayList<String> mGroupNames = new ArrayList<>();
// private ArrayList<String> mImageUrls = new ArrayList<>();

 FloatingActionButton fab_add;

 private ArrayList<Object> objects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, FAB_Menu_page.class));
            }
        });

        setBottomNavigationView();
        //getImages();

        //horizontal and vertical recyclerview
        RecyclerView recyclerView = findViewById(R.id.home_recycler_view);
        AdapterHome adapterHome = new AdapterHome(this, getObject());
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

        private ArrayList<Object> getObject() {
            objects.add(getGroupData().get(0));
            objects.add(getEventData().get(0));
            System.out.println(objects);
            return objects;
        }


        public static ArrayList<ModelHomeEvent> getEventData(){
            ArrayList<ModelHomeEvent> modelHomeEvents = new ArrayList<>();

            modelHomeEvents.add(new ModelHomeEvent("Tour", "Settled","123.0","12.0","2323"));
            modelHomeEvents.add(new ModelHomeEvent("Birthday", "Not Settled", "234.0","123.7","1212"));
            modelHomeEvents.add(new ModelHomeEvent("Party", "Not Settled", "568.9","67890.0","1212"));
            modelHomeEvents.add(new ModelHomeEvent("Tour", "Settled","123.0","12.0","2345"));
            modelHomeEvents.add(new ModelHomeEvent("Birthday", "Not Settled", "234.0","123.7","23234"));
            modelHomeEvents.add(new ModelHomeEvent("Party", "Not Settled", "568.9","67890.0","0"));

            return modelHomeEvents;
        }


        public static ArrayList<ModelHomeGroup> getGroupData() {
            ArrayList<ModelHomeGroup> modelHomeGroups = new ArrayList<>();
            modelHomeGroups.add(new ModelHomeGroup("Kathford", R.mipmap.ic_applogo));
            modelHomeGroups.add(new ModelHomeGroup("Lolwa", R.mipmap.ic_launcher));
            modelHomeGroups.add(new ModelHomeGroup("Aimless", R.mipmap.ic_launcher_foreground));
            modelHomeGroups.add(new ModelHomeGroup("Kathford", R.mipmap.ic_applogo));
            modelHomeGroups.add(new ModelHomeGroup("Lolwa", R.mipmap.ic_launcher));
            modelHomeGroups.add(new ModelHomeGroup("Aimless", R.mipmap.ic_launcher_foreground));

            return modelHomeGroups;
        }



//    private void getImages(){
//
//        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//        mGroupNames.add("Havasu Falls");
//
//        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//        mGroupNames.add("Trondheim");
//
//        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
//        mGroupNames.add("Portugal");
//
//        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
//        mGroupNames.add("Rocky Mountain National Park");
//
//
//        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
//        mGroupNames.add("Mahahual");
//
//        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
//        mGroupNames.add("Frozen Lake");
//
//
//        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
//        mGroupNames.add("White Sands Desert");
//
//        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
//        mGroupNames.add("Austrailia");
//
//        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
//        mGroupNames.add("Washington");
//
//        initRecyclerView();
//
//    }
//
//    private void initRecyclerView(){
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
//        RecyclerView recyclerView = findViewById(R.id.group_recycler_view);
//        recyclerView.setLayoutManager(layoutManager);
//        RecycleViewAdapterHomeGroup adapter = new RecycleViewAdapterHomeGroup(this, mGroupNames, mImageUrls);
//        recyclerView.setAdapter(adapter);
//    }




    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(Home.this, Group.class); // Activity_num = 1
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_event:
                        Intent intent3 = new Intent(Home.this, Event.class); // Activity_num = 3
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(Home.this, Profile.class); // Activity_num = 4
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
