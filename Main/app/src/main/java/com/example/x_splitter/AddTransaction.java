package com.example.x_splitter;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {
    TextView TextViewAddTransaction;
    TextView TextViewSave;
    EditText TextViewAmount;
    TextView TextViewDate;
    EditText TextViewCategory;
    EditText TextViewEvent;
    EditText TextViewPaidBy;
    EditText TextViewNote;
    DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        TextViewAmount = (EditText) findViewById(R.id.text_view_amount);

        TextViewCategory = (EditText) findViewById(R.id.text_view_category);
        TextViewEvent = (EditText) findViewById(R.id.text_view_event);
        TextViewPaidBy = (EditText) findViewById(R.id.text_view_paidby);
        TextViewNote = (EditText) findViewById(R.id.text_view_note);

        TextViewDate = (TextView) findViewById(R.id.text_view_date);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                TextViewDate.setText(date);
            }
        };

        TextViewAddTransaction = (TextView)findViewById(R.id.text_view_add_transaction);
        TextViewSave = (TextView)findViewById(R.id.textView_save);

        TextViewAddTransaction.setOnClickListener(this);
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

    private void addTransaction(){

    }

    private void saveTransaction(){
        int amount = (Integer.parseInt(TextViewAmount.getText().toString().trim()));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view_date:
                dateSelector();
                break;

            case R.id.text_view_add_transaction:
                addTransaction();
                break;

            case R.id.textView_save:
                saveTransaction();
                break;
        }

    }
}
