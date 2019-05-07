package com.example.x_splitter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static android.provider.ContactsContract.CommonDataKinds.*;


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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.edit_text_confirm_password);
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
                ConnectMySql connectMySql = new ConnectMySql();
                connectMySql.execute("");
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
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Email pattern is wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Username.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.length() < 4) {
            Toast.makeText(this, "Minimum password length is 4", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String Email = editTextEmail.getText().toString().trim();
        String Username = editTextUsername.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();
        Connection con = null;
        PreparedStatement pst = null;

        @Override
        protected void onPreExecute(){
        super.onPreExecute();
        Toast.makeText(SignupActivity.this, "Registering...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://192.168.10.5:3306/users_db", "srijal", "");
                System.out.println("Database Connection Success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String res = "Database Connected Successfully";
            String query = "insert into users_info(username, email, password) values (?,?,?)";

            try {
                pst = con.prepareStatement(query);
                pst.setString(1, Email);
                pst.setString(2, Username);
                pst.setString(3, Password);
                pst.executeUpdate();
                con.close();
                pst.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            return "Registered";

        }


    }



}



