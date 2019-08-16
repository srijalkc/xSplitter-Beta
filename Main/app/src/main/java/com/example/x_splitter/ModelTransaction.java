package com.example.x_splitter;

import java.util.Date;

public class ModelTransaction {
    private String date;
    private String transac_money;
    private String transac_category;

    public ModelTransaction(String date,  String transac_money, String transac_category) {
        this.date = date;

        this.transac_money = transac_money;

        this.transac_category = transac_category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getTransac_money() {
        return transac_money;
    }

    public void setTransac_money(String transac_money) {
        this.transac_money = transac_money;
    }


    public String getTransac_category() {
        return transac_category;
    }

    public void setTransac_category(String transac_category) {
        this.transac_category = transac_category;
    }
}
