package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class BottomNavigationHelper {
    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, Home.class); // Activity_num = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_group:
                        Intent intent2 = new Intent(context, Group.class); // Activity_num = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_event:
                        Intent intent3 = new Intent(context, Event.class); // Activity_num = 3
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(context, Profile.class); // Activity_num = 4
                        context.startActivity(intent4);
                        break;

                }
                return false;
            }
        });
    }
}
