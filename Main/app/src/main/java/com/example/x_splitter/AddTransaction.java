package com.example.x_splitter;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {
    TextView TextViewSave;
    EditText TextViewAmount;
    TextView TextViewDate;
    EditText TextViewCategory;
    EditText TextViewPaidBy;
    Spinner SpinnerPaidBy;
    EditText TextViewNote;
    ImageButton btn_back;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    DatabaseReference databaseTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        btn_back = findViewById(R.id.image_button_back);



        databaseTransaction = FirebaseDatabase.getInstance().getReference("Transaction");

        TextViewAmount = (EditText) findViewById(R.id.text_view_amount);

        TextViewCategory = (EditText) findViewById(R.id.text_view_category);
        TextViewPaidBy = (EditText) findViewById(R.id.text_view_paidby);
        //TextViewEvent = (EditText) findViewById(R.id.text_view_event);

        //SpinnerPaidBy = (Spinner) findViewById(R.id.spinner_paidby);

        TextViewNote = (EditText) findViewById(R.id.text_view_note);


        //Spinner spinner_split = findViewById(R.id.spinner_split);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.splits, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spinner_split.setAdapter(adapter);
        //spinner_split.setOnItemSelectedListener(this);


        TextViewDate = (TextView) findViewById(R.id.text_view_date);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTransaction.super.onBackPressed();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                TextViewDate.setText(date);
            }
        };

        TextViewSave = (TextView)findViewById(R.id.textView_save);

        TextViewSave.setOnClickListener(this);
        TextViewDate.setOnClickListener(this);
    }

    private void dateSelector(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddTransaction.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void saveTransaction(){
        String amount = TextViewAmount.getText().toString().trim();
        String date = TextViewDate.getText().toString().trim();
        //String event = TextViewEvent.getText().toString().trim();
        String category = TextViewCategory.getText().toString().trim();
        String paidBy = TextViewPaidBy.getText().toString().trim();
        String note = TextViewNote.getText().toString().trim();

        if(amount.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if(date.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please choose Date", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(event.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Please enter event", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if(category.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Category", Toast.LENGTH_SHORT).show();
            return;
        }
        if(note.isEmpty()){
            Toast.makeText(getApplicationContext(), "No Note added", Toast.LENGTH_SHORT).show();
        }

        String id = databaseTransaction.push().getKey();
        //TransactionInfo transactionInfo = new TransactionInfo(amount, date, event, category, paidBy, note);
        TransactionInfo transactionInfo = new TransactionInfo(amount, date, category, paidBy, note);
        databaseTransaction.child(id).setValue(transactionInfo);
        Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view_date:
                dateSelector();
                break;

            case R.id.textView_save:
                saveTransaction();
                break;
        }

    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//        String text = parent.getItemAtPosition(position).toString();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//    }

}
