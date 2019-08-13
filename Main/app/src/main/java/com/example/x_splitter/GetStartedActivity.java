package com.example.x_splitter;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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
