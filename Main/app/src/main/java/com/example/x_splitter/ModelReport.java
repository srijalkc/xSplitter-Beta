package com.example.x_splitter;

public class ModelReport {
    private String user1;
    private String user2;
    private double amount;


    public ModelReport(String user1,double amount, String user2) {
        this.user1= user1;
        this.amount=amount;
        this.user2=user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
