package com.example.myapplication;

import java.util.Calendar;

/**
 * Store.
 */
public class Store {
    private String title;
    private int amount;
    private int type;
    private String detail;
    private Calendar date;
    public Store(String setTitle, int setAmount, int setType, String setDetail, Calendar setDate) {
        title = setTitle;
        amount = setAmount;
        detail = setDetail;
        type = setType;
        detail = setDetail;
        date = setDate;
    }

    public String getTitle() {
        return title;
    }
    public int getAmount(){
        return amount;
    }

    public int getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public Calendar getDate() {
        return date;
    }
}
