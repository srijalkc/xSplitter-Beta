package com.example.x_splitter;

public class ModelReport {
    private String category_name;
    private String category_total;

    public ModelReport(String category_name, String category_total) {
        this.category_name = category_name;
        this.category_total = category_total;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_total() {
        return category_total;
    }

    public void setCategory_total(String category_total) {
        this.category_total = category_total;
    }
}
