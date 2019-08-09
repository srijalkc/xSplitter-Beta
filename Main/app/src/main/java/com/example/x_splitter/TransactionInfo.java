package com.example.x_splitter;

public class TransactionInfo {
    String amount;
    String date;
    String category;
    String group;
    String event;
    String paidBy;

    public TransactionInfo(String amount, String date, String category, String group, String event, String paidBy) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.group = group;
        this.event = event;
        this.paidBy = paidBy;
    }

    public TransactionInfo(String paidBy, String amount){
        this.paidBy = paidBy;
        this.amount = amount;
    }

    public TransactionInfo() {
    }



}

