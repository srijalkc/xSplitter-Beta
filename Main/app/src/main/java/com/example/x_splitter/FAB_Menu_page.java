package com.example.x_splitter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FAB_Menu_page extends AppCompatActivity {
TextView btn_add_group;
FloatingActionButton btn_fab_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab__menu_page);

        btn_add_group = findViewById(R.id.btn_fab_group);
        btn_fab_cancel = findViewById(R.id.fab_cancel);

        btn_add_group.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Create_group.class));
                finish();
            }
        });

        btn_fab_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAB_Menu_page.super.onBackPressed();
            }
        });
    }
}
