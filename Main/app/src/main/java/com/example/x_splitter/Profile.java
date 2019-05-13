package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Profile extends AppCompatActivity {

    private static final int Activity_num = 4; // for recognizing menu item number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setBottomNavigationView();
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        BottomNavigationHelper.enableNavigation(Profile.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}

