package com.example.x_splitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;


public class SignupActivity extends AppCompatActivity  {
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private Button buttonSignUpGmail;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private String userID;
    Query usernameQuery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.edit_text_confirm_password);
        buttonSignUp = (Button) findViewById(R.id.button_signup);
        //buttonSignUpGmail = (Button) findViewById(R.id.button_signup_gmail);

        progressDialog = new ProgressDialog(this);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        //buttonSignUpGmail.setOnClickListener(this);

    }

    public void registerUser() {
        String Email = editTextEmail.getText().toString().trim();
        String Username = editTextUsername.getText().toString().trim();
        usernameQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(Username);
        String Password = editTextPassword.getText().toString().trim();
        String ConfirmPassword = editTextConfirmPassword.getText().toString().trim();
        final Pattern PasswordPattern = Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[!@#$%^&*])"+
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

        if (Email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Email pattern is wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Username.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Username.contains(" ")){
            Toast.makeText(this, "You cannot use space", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!PasswordPattern.matcher(Password).matches()){
            Toast.makeText(this, "Password too weak", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Your password should contain atleast 1 special character, 1 upper case letter, 1 lowercase letter and 1 number");
            return;
        }
        if (ConfirmPassword.isEmpty()) {
            Toast.makeText(this, "confirm Your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
            return;
        }

        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()>0){
                    Toast.makeText(getBaseContext(), "Choose Different Username", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            UsersInfo usersinfo = new UsersInfo(Email, Username);
                            if(task.isSuccessful()){
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(usersinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(getApplicationContext(), "User Registered Successful. Please Verify Your Email", Toast.LENGTH_SHORT).show();
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                                finish();
                                                            }
                                                        }, 2000);
                                                    }
                                                    else{
                                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else{
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(getApplicationContext(), "Email already registered", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //@Override
//    public void onClick(View v) {
//        if (v.getId()== R.id.button_signup) {
//                registerUser();
//        }
//    }
}




























//    @SuppressLint("StaticFieldLeak")
//    private class ConnectMySql extends AsyncTask<String, Void, String> {
//        String Email = editTextEmail.getText().toString().trim();
//        String Username = editTextUsername.getText().toString().trim();
//        String Password = editTextPassword.getText().toString().trim();
//        Connection con = null;
//        PreparedStatement pst = null;
//
//        @Override
//        protected void onPreExecute(){
//        super.onPreExecute();
//        Toast.makeText(SignupActivity.this, "Registering...", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                con = DriverManager.getConnection("jdbc:mysql://192.168.10.5:3306/users_db", "srijal", "");
//                System.out.println("Database Connection Success");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String res = "Database Connected Successfully";
//            String query = "insert into users_info(username, email, password) values (?,?,?)";
//
//            try {
//                pst = con.prepareStatement(query);
//                pst.setString(1, Email);
//                pst.setString(2, Username);
//                pst.setString(3, Password);
//                pst.executeUpdate();
//                con.close();
//                pst.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            return "Registered";
//
//        }
//
//
//    }


