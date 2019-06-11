package com.example.x_splitter;

public class TransactionInfo {
    String amount;
    String date;
    //String event;
    String category;
    String paidBy;
    String note;

    public TransactionInfo() {
    }

    //public TransactionInfo(String amount, String date, String event, String category, String paidBy, String note) {
    public TransactionInfo(String amount, String date, String category, String paidBy, String note) {
        this.amount = amount;
        this.date = date;
        //this.event = event;
        this.category = category;
        this.paidBy = paidBy;
        this.note = note;
    }
}

