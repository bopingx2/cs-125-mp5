package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Type {
    public List<String> types = new ArrayList<String>() {
        {
            add("Receiving");
            add("Dinning");
            add("Grocery");
            add("Entertainment");
            add("Electronics");
            add("Transportation");
            add("Clothing");
            add("Others");
        }};
    public int getType(String type) {
        if (type.equals("Receiving")) {
            return 0;
        } else if (type.equals("Dinning")) {
            return 1;
        } else if (type.equals("Grocery")) {
            return 2;
        } else if (type.equals("Entertainment")){
            return 3;
        } else if (type.equals("Electronics")) {
            return 4;
        } else if (type.equals("Transportation")) {
            return 5;
        } else if (type.equals("Clothing")) {
            return 6;
        } else {
            return 7;
        }
    }
    public String getType(int type) {
        if (type == 0) {
            return "Receiving";
        } else if (type == 1) {
            return "Dinning";
        } else if (type == 2) {
            return "Grocery";
        } else if (type == 3){
            return "Entertainment";
        } else if (type == 4) {
            return "Electronics";
        } else if (type == 5) {
            return "Transportation";
        } else if (type == 6) {
            return "Clothing";
        } else {
            return "Others";
        }
    }
}
