package com.finance.model;

import java.util.Date;

public class Income {
    private double value;
    private Date date;
    private String category;

    public Income(double value, Date date, String category) {
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}