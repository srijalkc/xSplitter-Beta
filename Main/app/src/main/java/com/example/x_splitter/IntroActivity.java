package com.example.x_splitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext,btnGetStarted;
    TextView btnSkip;
    int position= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check if it is opened !st time or not

        if(restorePrefData()){
            //open getStarted page
            Intent getstarted = new Intent(getApplicationContext(), GetStartedActivity.class);
            startActivity(getstarted);
            finish();
        }

        setContentView(R.layout.activity_intro);

        //ini views
        btnNext = findViewById(R.id.button_intro_next);
        btnSkip = findViewById(R.id.button_intro_skip);
        btnGetStarted = findViewById(R.id.button_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);

        //fill list screen

        final List<IntroScreenItem> mList = new ArrayList<>();
        mList.add(new IntroScreenItem("Group",
                "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. ",
                R.drawable.undraw_group_selfie_ijc6));
        mList.add(new IntroScreenItem("Events",
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. ",
                R.drawable.undraw_having_fun_iais));
        mList.add(new IntroScreenItem("Split",
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. ",
                R.drawable.undraw_make_it_rain_iwk4));
        mList.add(new IntroScreenItem("Report",
                "Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. ",
                R.drawable.undraw_data_report_bi6l));
        mList.add(new IntroScreenItem("Save Time",
                "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. ",
                R.drawable.undraw_time_management_30iu));

        //setup viewpager
        screenPager = findViewById(R.id.view_pager_image);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        screenPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position<mList.size()-1){
                    btnNext.setEnabled(true);
                    btnNext.setVisibility(View.VISIBLE);
                }
                if(position==mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //next button click listener

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == (mList.size() - 1)) {
                    loadLastScreen();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GetStartedActivity.class));
                finish();
            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //open getStarted page
                startActivity(new Intent(getApplicationContext(),GetStartedActivity.class));

                //need to keep boolean value to know if user is seeing intro for 1st time
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myprefes",MODE_PRIVATE);
        return (pref.getBoolean("isIntroOpened",false));
    }

    private void savePrefsData(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myprefes",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.apply();
    }

    //show getstarted button by hiding next and indicator
    private void loadLastScreen(){

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);

    }
}
