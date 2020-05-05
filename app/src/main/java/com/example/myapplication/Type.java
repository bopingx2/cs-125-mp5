package com.example.myapplication;

public class Type {
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
}
