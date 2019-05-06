package com.example.x_splitter;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        tabLayout = findViewById(R.id.tab_login_signup);
        viewPager = findViewById(R.id.view_pager_login_signup);
        GetStartedViewPagerAdapter adapter = new GetStartedViewPagerAdapter(getSupportFragmentManager());

        //adding fragments
        adapter.AddFragment(new FragmentSignUp(),"Sign UP");
        adapter.AddFragment(new FragmentLogin(),"Log In");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
