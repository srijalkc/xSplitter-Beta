package com.example.x_splitter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FAB_Menu_page extends AppCompatActivity {
TextView btn_add_group, btn_add_transaction, btn_add_event;
FloatingActionButton btn_fab_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab__menu_page);

        btn_add_group = findViewById(R.id.btn_fab_group);
        btn_add_event = findViewById(R.id.btn_fab_event);
        btn_add_transaction = findViewById(R.id.btn_fab_transaction);
        btn_fab_cancel = findViewById(R.id.fab_cancel);

        btn_add_group.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddGroup.class));
                finish();
            }
        });

        btn_add_event.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddEvent.class));
                finish();
            }
        });

        btn_add_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddTransaction.class));
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
