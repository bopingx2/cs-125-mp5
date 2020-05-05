package com.example.myapplication;

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
}
