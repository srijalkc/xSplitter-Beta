package com.example.x_splitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Group extends AppCompatActivity {

    private static final int Activity_num = 1; // for recognizing menu item number

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setBottomNavigationView();
    }

    private void setBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);
        BottomNavigationHelper.enableNavigation(Group.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}
