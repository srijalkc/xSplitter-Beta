package com.example.x_splitter;

public class TransactionInfo {
    String amount;
    String date;
    //String event;
    String category;
    String paidBy;

    public TransactionInfo() {
    }


    public TransactionInfo(String amount, String date, String category, String paidBy) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.paidBy = paidBy;
    }
}

