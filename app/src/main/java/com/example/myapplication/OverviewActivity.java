package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private Spinner choseDate, choseCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        data.clear();
        loadList();
        currYear = Calendar.getInstance().get(Calendar.YEAR);
        currMonth = Calendar.getInstance().get(Calendar.MONTH);
        showItem();
        choseDate = findViewById(R.id.spinner);
        choseCat = findViewById(R.id.spinner2);
        List<String> type = new Type().types;
        type.add(0, "All");
        ArrayAdapter<String> catAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        catAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choseCat.setAdapter(catAda);
        List<String> date = new ArrayList<>();
        if (data == null || data.size() == 0) {
            date.add("All");
        } else {
            date.add("All");
            for (int i = 0; i < data.size(); i++) {
                Store read = data.get(i);
                if (!(date.contains(String.valueOf(read.getYear())))) {
                    date.add(String.valueOf(read.getYear()));
                }
                String month = String.format("%d/%d", read.getMonth() + 1, read.getYear());
                if (!(date.contains(month))) {
                    date.add(month);
                }
            }
        }
        ArrayAdapter<String> dateAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, date);
        dateAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choseDate.setAdapter(dateAda);
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

    public void showItem(int year, int month, String type) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getYear() == year) {
                if (search.getMonth() == month) {
                    if (search.getType() == new Type().getType(type)) {
                        empty = false;
                        none.setVisibility(View.GONE);
                        item.setVisibility(View.VISIBLE);
                        loadChunk(item, search);
                        if (search.getType() > 0) {
                            count -= search.getAmount();
                        } else {
                            count += search.getAmount();
                        }
                        money.setText(String.valueOf(count));
                    }
                }
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
        }
    }

    public void showItem(int year, int month) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getYear() == year) {
                if (search.getMonth() == month) {
                    empty = false;
                    none.setVisibility(View.GONE);
                    item.setVisibility(View.VISIBLE);
                    loadChunk(item, search);
                    if (search.getType() > 0) {
                        count -= search.getAmount();
                    } else {
                        count += search.getAmount();
                    }
                    money.setText(String.valueOf(count));
                }
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
        }
    }

    public void showItem(int year, String type) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getYear() == year) {
                if (search.getType() == new Type().getType(type)) {
                    empty = false;
                    none.setVisibility(View.GONE);
                    item.setVisibility(View.VISIBLE);
                    loadChunk(item, search);
                    if (search.getType() > 0) {
                        count -= search.getAmount();
                    } else {
                        count += search.getAmount();
                    }
                    money.setText(String.valueOf(count));
                }
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
        }
    }

    public void showItem(int year) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getYear() == year) {
                empty = false;
                none.setVisibility(View.GONE);
                item.setVisibility(View.VISIBLE);
                loadChunk(item, search);
                if (search.getType() > 0) {
                    count -= search.getAmount();
                } else {
                    count += search.getAmount();
                }
                money.setText(String.valueOf(count));
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
        }
    }

    public void showItem(String type) {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getType() == new Type().getType(type)) {
                empty = false;
                none.setVisibility(View.GONE);
                item.setVisibility(View.VISIBLE);
                loadChunk(item, search);
                if (search.getType() > 0) {
                    count -= search.getAmount();
                } else {
                    count += search.getAmount();
                }
                money.setText(String.valueOf(count));
            }
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
        }
    }

    public void showItem() {
        LinearLayout item = findViewById(R.id.item);
        TextView none = findViewById(R.id.textView17);
        TextView money = findViewById(R.id.money);
        item.setVisibility(View.GONE);
        item.removeAllViews();
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        double count = 0;
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            empty = false;
            none.setVisibility(View.GONE);
            item.setVisibility(View.VISIBLE);
            loadChunk(item, search);
            if (search.getType() > 0) {
                count -= search.getAmount();
            } else {
                count += search.getAmount();
            }
            money.setText(String.valueOf(count));
        }
        if (empty) {
            item.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
            money.setText("--");
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
        if (item.getType() > 0) {
            money.setText("-" + String.valueOf(item.getAmount()));
        } else {
            money.setText("+" + String.valueOf(item.getAmount()));
        }
        toAdd.addView(chunk);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int year;
        int month;
        String type;
        Spinner date, cat;
        date = findViewById(R.id.spinner);
        cat = findViewById(R.id.spinner2);
        if (!(date.getSelectedItem().toString().equals("All"))) {
            if (date.getSelectedItem().toString().contains("/")) {
                String[] str = date.getSelectedItem().toString().split("/");
                month = Integer.parseInt(str[0]) - 1;
                year = Integer.parseInt(str[1]);
            } else {
                year = Integer.parseInt(date.getSelectedItem().toString());
                month = 0;
            }
        } else {
            year = 0;
            month = 0;
        }
        if (!(cat.getSelectedItem().toString().equals("All"))) {
            type = cat.getSelectedItem().toString();
        } else {
            type = "All";
        }
        int viewid = parent.getId();
        if (viewid == R.id.spinner) {
            if (!(parent.getSelectedItem().toString().equals("All"))) {
                if (parent.getSelectedItem().toString().contains("/")) {
                    String[] str = parent.getSelectedItem().toString().split("/");
                    month = Integer.parseInt(str[0]) - 1;
                    year = Integer.parseInt(str[1]);
                } else {
                    year = Integer.parseInt(parent.getSelectedItem().toString());
                }
            }
        }
        if (viewid == R.id.spinner2) {
            if (!(parent.getSelectedItem().toString().equals("All"))) {
                type = parent.getSelectedItem().toString();
            }
        }

        System.out.println(year + " " + month + " " + type);
        if (year == 0 && month == 0) {
            if (type.equals("All")) {
                showItem();
            } else {
                showItem(type);
            }
        } else if (month == 0) {
            if (type.equals("All")) {
                showItem(year);
            } else {
                showItem(year, type);
            }
        } else {
            if (type.equals("All")) {
                showItem(year, month);
            } else {
                showItem(year, month, type);
            }
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) { }

}
