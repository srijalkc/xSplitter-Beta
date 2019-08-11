package com.example.x_splitter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TransactionInfo {
    String amount;
    String date;
    String category;
    String group;
    String event;
    String paidBy;
    String amountToPay;
    String amountToGet;
    String amountInvested;
    String amountInvestedTemp;
//List<String> groupMembers;
    //int equallySplittedAmount;


    //String ItemPaidBy;
    //int membersize;




    public TransactionInfo( int equallySplittedAmount, String amountToPay, String amountToGet, String amountInvested) {
        this.amountInvested = amountInvested;
        amountInvestedTemp = amountInvested;
        this.amountToGet = Integer.toString(equallySplittedAmount);

//        int difference = ((Integer.parseInt(amountInvested)) - equallySplittedAmount);
//        if(difference>=0)
//            {
//                this.amountToGet =Integer.toString(difference);

//                if(Integer.parseInt(amountToPay) != 0){
//                    if (Integer.parseInt(amountToGet)<Integer.parseInt(amountToPay)){
//                        this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-(Integer.parseInt(amountToGet)));
//                        this.amountToGet=Integer.toString(0);
//                    }
//                    else {
//                        this.amountToGet=Integer.toString((Integer.parseInt(amountToGet))-(Integer.parseInt(amountToPay)));
//                        this.amountToPay=Integer.toString(0);
//                    }
//                }
//            }else {
//            this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-difference);
//            this.amountToGet=Integer.toString(0);

//        }
            }

    public TransactionInfo( int equallySplittedAmount, String itemPaidBy, String amountToPay, String amountToGet, String amountInvested) {
        this.amountToPay = Integer.toString(equallySplittedAmount);
    }

//    public TransactionInfo(String amountToPay, String amountToGet, String amountInvested) {
//        this.amountToPay = amountToPay;
//        this.amountToGet = amountToGet;
//        this.amountInvested = amountInvested;
//    }
//
//    public TransactionInfo(String amount, String date, String category, String group, String event, String paidBy) {
//        this.amount = amount;
//        this.date = date;
//        this.category = category;
//        this.group = group;
//        this.event = event;
//        this.paidBy = paidBy;
//    }
//
//    public TransactionInfo(String paidBy, String amount){
//        this.paidBy = paidBy;
//        this.amount = amount;
//    }

//    public TransactionInfo() {
//    }

    public TransactionInfo( String amountInvested){
        this.amountInvested = amountInvested + amountInvestedTemp;


    }


}

