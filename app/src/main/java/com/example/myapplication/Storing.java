package com.example.myapplication;

import java.util.List;
import java.util.Date;

public class Storing {
    private List<Store> list;
    public Storing(List<Store> setList) {
        list =setList;
    }
    public int total() {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getAmount();
        }
        return sum;
    }
    public List<Store> dateSort() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getDate().getTime().compareTo(list.get(j + 1).getDate().getTime()) > 0) {
                    Store temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
    public List<Store> amountSort() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getAmount() > list.get(j + 1).getAmount()) {
                    Store temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
}
