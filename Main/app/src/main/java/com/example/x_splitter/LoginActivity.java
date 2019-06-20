package com.example.x_splitter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Username;
    EditText Password;
    Button ButtonLogin;
    Button ButtonLoginWithGmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Username = (EditText)findViewById(R.id.edit_text_login_username);
        Password = (EditText)findViewById(R.id.edit_text_login_password);
        ButtonLogin = (Button)findViewById(R.id.button_login);
        ButtonLoginWithGmail = (Button)findViewById(R.id.button_login_gmail);

        ButtonLogin.setOnClickListener(this);
        ButtonLoginWithGmail.setOnClickListener(this);



    }

    private void loginUser(){
        String username = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_login:
                loginUser();
                break;


            case R.id.button_login_gmail:
                break;
        }
    }
}

