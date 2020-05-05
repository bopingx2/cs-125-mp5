package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Dialog addDialog;
    private Button addButton, overviewButton, done;
    private EditText titleAdd, amountAdd, descriptionAdd;
    private Spinner typeAdd;
    private static List<Store> data = new ArrayList<>();
    private int currYear, currMonth, currDate;
    private int choseYear, choseMonth, choseDate;
    private int setYear, setMonth, setDay;
    private TextView dateChoose, dateAdd;
    private List<String> types = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        types.add("Receiving");
        types.add("Dinning");
        types.add("Grocery");
        types.add("Entertainment");
        types.add("Electronics");
        types.add("Transportation");
        types.add("Clothing");
        types.add("Others");

        currYear = Calendar.getInstance().get(Calendar.YEAR);
        currMonth = Calendar.getInstance().get(Calendar.MONTH);
        currDate = Calendar.getInstance().get(Calendar.DATE);

        choseYear = currYear;
        choseMonth = currMonth;
        choseDate = currDate;

        addDialog = new Dialog(this);

        addButton = findViewById(R.id.button);
        overviewButton = findViewById(R.id.button2);

        dateChoose = findViewById(R.id.textView4);
        String currTime = String.format("%d/%d/%d", currMonth + 1, currDate, currYear);
        dateChoose.setText(currTime);

        addButton.setOnClickListener(v -> {
            ShowAddPopup();
        });
        overviewButton.setOnClickListener(v -> {
            Intent overview = new Intent(this, OverviewActivity.class);
            startActivity(overview);
        });
        dateChoose.setOnClickListener(v -> {
            DatePickerDialog datePick = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    choseYear = year;
                    choseDate = dayOfMonth;
                    choseMonth = month;
                    String d = String.format("%d/%d/%d", month + 1, dayOfMonth, year);
                    dateChoose.setText(d);
                }
            }, currYear, currMonth, currDate);
            datePick.show();
        });

        showItem(choseYear, choseMonth, choseDate);

    }
    public void ShowAddPopup() {
        setYear = currYear;
        setMonth = currMonth;
        setDay = currDate;

        addDialog.setContentView(R.layout.popup_add);

        typeAdd = addDialog.findViewById(R.id.spinner3);
        typeAdd.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdd.setAdapter(adapter);

        done = addDialog.findViewById(R.id.button3);

        titleAdd = addDialog.findViewById(R.id.editText2);
        amountAdd = addDialog.findViewById(R.id.editText7);
        descriptionAdd = addDialog.findViewById(R.id.editText5);

        dateAdd = addDialog.findViewById(R.id.textView16);
        String currTime = String.format("%d/%d/%d", currMonth + 1, currDate, currYear);
        dateAdd.setText(currTime);

        addDialog.show();

        dateAdd.setOnClickListener(v -> {
            DatePickerDialog datePick = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    setYear = year;
                    setMonth = month;
                    setDay = dayOfMonth;

                    String d = String.format("%d/%d/%d", month + 1, dayOfMonth, year);
                    dateAdd.setText(d);
                }
            }, currYear, currMonth, currDate);
            datePick.show();
        });

        done.setOnClickListener(v -> {
            String setTitle = titleAdd.getText().toString();
            double setAmount = Double.valueOf(amountAdd.getText().toString());
            String setStrType = typeAdd.getSelectedItem().toString();
            int setType = new Type().getType(setStrType);
            String setDetail = descriptionAdd.getText().toString();
            Store store = new Store(setTitle, setAmount, setType, setDetail, setYear, setMonth, setDay);
            data.add(store);
            showItem(choseYear, choseMonth, choseDate);
            addDialog.dismiss();
        });


    }

    public void showItem(int year, int month, int date) {
        LinearLayout spend = findViewById(R.id.spend);
        LinearLayout spending = findViewById(R.id.spending);
        LinearLayout receive = findViewById(R.id.recieve);
        LinearLayout receiving = findViewById(R.id.recieving);
        LinearLayout none = findViewById(R.id.none);
        spend.setVisibility(View.GONE);
        spending.removeAllViews();
        receiving.removeAllViews();
        receive.setVisibility(View.GONE);
        none.setVisibility((View.VISIBLE));
        boolean empty = true;
        System.out.println(data.size());
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            System.out.println(search.getType());
            if (search.getYear() == year) {
                if (search.getMonth() == month) {
                    if (search.getDate() == date) {
                        empty = false;
                        none.setVisibility(View.GONE);
                        if (search.getType() > 0) {
                            spend.setVisibility(View.VISIBLE);
                            View chunk = getLayoutInflater().inflate(R.layout.chunk_item, spending, false);
                            TextView event = chunk.findViewById(R.id.event);
                            TextView detail = chunk.findViewById(R.id.detail);
                            TextView money = chunk.findViewById(R.id.money);
                            event.setText(search.getTitle());
                            detail.setText(search.getDetail());
                            money.setText(String.valueOf(search.getAmount()));
                            chunk.setOnClickListener(v -> {
                                addDialog.setContentView(R.layout.popup_info);

                                addDialog.show();
                            });
                            spending.addView(chunk);
                        } else {
                            receive.setVisibility(View.VISIBLE);
                            View chunk = getLayoutInflater().inflate(R.layout.chunk_item, receiving, false);
                            TextView event = chunk.findViewById(R.id.event);
                            TextView detail = chunk.findViewById(R.id.detail);
                            TextView money = chunk.findViewById(R.id.money);
                            event.setText(search.getTitle());
                            detail.setText(search.getDetail());
                            money.setText(String.valueOf(search.getAmount()));
                            receiving.addView(chunk);
                        }
                    }
                }
            }
        }
        if (empty) {
            spend.setVisibility(View.GONE);
            receive.setVisibility(View.GONE);
            none.setVisibility((View.VISIBLE));
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }
    public void onNothingSelected(AdapterView<?> arg0) { }
}
