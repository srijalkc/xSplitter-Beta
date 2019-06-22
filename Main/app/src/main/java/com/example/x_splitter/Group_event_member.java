package com.example.x_splitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Group_event_member extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_event_member);

        tabLayout = findViewById(R.id.tab_event_member);
        viewPager = findViewById(R.id.view_pager_event_member);

        AdapterGroup_event_member adapter = new AdapterGroup_event_member(getSupportFragmentManager());

        //adding fragments
        adapter.AddFragment(new FragmentEvent(),"Events");
        adapter.AddFragment(new FragmentMember(),"Members");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
