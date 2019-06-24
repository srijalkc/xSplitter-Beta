package com.example.x_splitter;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class AddGroup extends AppCompatActivity {

    ImageButton btn_back;
    Button buttonAddGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        btn_back = findViewById(R.id.image_button_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGroup.super.onBackPressed();
            }
        });

        buttonAddGroup = (Button)findViewById(R.id.button_add);
        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                LinearLayout linearLayout = findViewById(R.id.ll_add_group);
                View view = inflater.inflate(R.layout.activity_add_group_inflater, linearLayout, false);
                linearLayout.addView(view);
            }
        });
    }
}
