package com.example.x_splitter;

import android.app.ProgressDialog;
import android.icu.text.UnicodeSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private Button buttonSignUpGmail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail = (EditText) findViewById(R.id.edittext_email);
        editTextUsername = (EditText) findViewById(R.id.edittext_username);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.edittext_confirm_password);
        buttonSignUp = (Button) findViewById(R.id.button_signup);
        buttonSignUpGmail = (Button) findViewById(R.id.button_signup_gmail);

        progressDialog = new ProgressDialog(this);

        buttonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signup:
                registerUser();
                break;

        }

    }

    public void registerUser() {
        String Email = editTextEmail.getText().toString().trim();
        String Username = editTextUsername.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();
        String ConfirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (Email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            //editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Email pattern is wrong", Toast.LENGTH_SHORT).show();
            //editTextEmail.requestFocus();
            return;
        }
        if (Username.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            //editTextUsername.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            //editTextPassword.requestFocus();
            return;
        }
        if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
            //editTextConfirmPassword.requestFocus();
            return;
        }
        if (Password.length() < 4) {
            Toast.makeText(this, "Minimum password length is 4", Toast.LENGTH_SHORT).show();
            //editTextConfirmPassword.requestFocus();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        SignUpRegisterValues registerValues = new SignUpRegisterValues();
        registerValues.username= Username;
        registerValues.email= Email;
        registerValues.password= Password;
        registerValues.confirmPasswsord= ConfirmPassword;
        SignUpRegister register = new SignUpRegister();
        register.connect();
        register.addUsers(registerValues);
        progressDialog.setMessage("Registered");
        progressDialog.show();
    }
}



