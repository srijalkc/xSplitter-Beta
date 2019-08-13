package com.example.transactionpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static com.example.transactionpractice.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextTotal;
    EditText editTextGet;
    EditText editTextPay;
    TextView textViewName;
    TextView textViewTotal;
    TextView textViewGet;
    TextView textViewPay;
    Button buttonSubmit;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("Transaction");
        editTextName = (EditText)findViewById(R.id.editText_name);
        editTextTotal = (EditText)findViewById(R.id.editText_total);
        editTextGet = (EditText)findViewById(R.id.editText_get);
        editTextPay = (EditText)findViewById(R.id.editText_pay);
        textViewName = (TextView)findViewById(R.id.textView_name);
        textViewTotal = (TextView)findViewById(R.id.textView_total);
        textViewGet = (TextView)findViewById(R.id.textView_get);
        textViewPay = (TextView)findViewById(R.id.textView_pay);
        buttonSubmit = (Button)findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
    }

    public void addTransaction(){
        String name = editTextName.getText().toString().trim();
        int total = Integer.parseInt(editTextTotal.getText().toString().trim());
        int get = Integer.parseInt(editTextGet.getText().toString().trim());
        int pay = Integer.parseInt(editTextPay.getText().toString().trim());

        String ID = databaseReference.push().getKey();
        Transaction transaction = new Transaction(ID, name, total, get, pay);
        databaseReference.child(ID).setValue(transaction);
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

    }
}
