package com.example.transactionpractice;

public class Transaction {
    String name;
    String ID;
    int total;
    int pay;
    int get;

    public Transaction(){

    }

    public Transaction(String ID, String name, int total, int pay, int get) {
        this.ID = ID;
        this.name = name;
        this.total = total;
        this.pay = pay;
        this.get = get;
    }

    public String getID() {
        return ID;
    }
    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public int getPay() {
        return pay;
    }

    public int getGet() {
        return get;
    }
}
