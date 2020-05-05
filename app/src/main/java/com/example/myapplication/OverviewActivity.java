package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OverviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Intent main;
    private static List<Store> data = new ArrayList<>();
    private int currYear, currMonth;
    private int choseYear, choseMonth;
    private Spinner choseDate, choseCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        data.clear();
        loadList();
        currYear = Calendar.getInstance().get(Calendar.YEAR);
        currMonth = Calendar.getInstance().get(Calendar.MONTH);
        choseYear = currYear;
        choseMonth = currMonth;
        showItem(choseYear, choseMonth);
        choseDate = findViewById(R.id.spinner);
        choseCat = findViewById(R.id.spinner2);
        choseDate.setOnItemSelectedListener(this);
        choseCat.setOnItemSelectedListener(this);
    }

    public void loadList() {
        main = getIntent();
        int size = main.getIntExtra("size", 0);
        for (int i = 0; i < size; i++) {
            String title = main.getStringExtra("title" + i);
            double amount = main.getDoubleExtra("amount" + i, 0.0);
            int type = main.getIntExtra("type" + i, 0);
            String detail  = main.getStringExtra("detail" + i);
            int year = main.getIntExtra("year" + i, 0);
            int month = main.getIntExtra("month" + i, 0);
            int date = main.getIntExtra("date" + i, 0);
            Store save = new Store(title, amount, type, detail, year, month, date);
            data.add(save);
        }
    }

    public void showItem(int year, int month) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        System.out.println(data.isEmpty());
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            System.out.println(search.getType());
            System.out.println(search.getYear() == year);
            if (search.getYear() == year) {
                System.out.println(search.getMonth() == month);
                if (search.getMonth() == month) {
                    empty = false;
                    none.setVisibility(View.GONE);
                    item.setVisibility(View.VISIBLE);
                    loadChunk(item, search);
                }
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
        }
    }

    public void loadChunk(LinearLayout toAdd, Store item) {
        View chunk = getLayoutInflater().inflate(R.layout.chunk_overview_item, toAdd, false);
        TextView event = chunk.findViewById(R.id.event);
        TextView date = chunk.findViewById(R.id.date);
        TextView detail = chunk.findViewById(R.id.detail);
        TextView money = chunk.findViewById(R.id.money);
        event.setText(item.getTitle());
        detail.setText(item.getDetail());
        String dateItem = String.format("%d/%d/%d", item.getMonth() + 1, item.getDate(), item.getYear());
        date.setText(dateItem);
        money.setText(String.valueOf(item.getAmount()));
        toAdd.addView(chunk);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }
    public void onNothingSelected(AdapterView<?> arg0) { }

}
