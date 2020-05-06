package com.example.myapplication;

import android.content.Intent;

import java.util.Map;

/**
 * Store.
 */
public class Store{
    private String title;
    private double amount;
    private int type;
    private String detail;
    private int year;
    private int month;
    private int date;

    public Store(String setTitle, double setAmount, int setType, String setDetail, int setYear, int setMonth, int setDate) {
        title = setTitle;
        amount = setAmount;
        detail = setDetail;
        type = setType;
        detail = setDetail;
        year = setYear;
        month = setMonth;
        date = setDate;
    }

    public Store(Map<String, Object> map) {
        title = (String) map.get("title");
        amount = (Double) map.get("amount");
        detail = (String) map.get("detail");
        type = ((Long) map.get("type")).intValue();
        detail = (String) map.get("detail");
        year = ((Long) map.get("year")).intValue();
        month = ((Long) map.get("month")).intValue();
        date =  ((Long) map.get("date")).intValue();
    }

    public Store() { }

    public String getTitle() {
        return title;
    }

    public double getAmount(){
        return amount;
    }

    public int getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() { return month; }

    public int getDate() { return date; }

    public String toString() {
        return title + " " + " " + amount + " " + type + " " + detail;
    }
}
