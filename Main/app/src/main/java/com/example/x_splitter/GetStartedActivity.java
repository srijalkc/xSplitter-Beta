package com.example.x_splitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener {

     Button buttonSignup;
     Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        buttonSignup = (Button) findViewById(R.id.button_signup);
        buttonLogin = (Button) findViewById(R.id.button_login);

        buttonSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_signup:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }

    }
}
