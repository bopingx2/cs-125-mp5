package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Dialog addDialog;
    private Button addButton, overviewButton, done, logout;
    private EditText titleAdd, amountAdd, descriptionAdd;
    private Spinner typeAdd;
    private static List<Store> data = new ArrayList<>();
    private int currYear, currMonth, currDate;
    private int choseYear, choseMonth, choseDate;
    private int setYear, setMonth, setDay;
    private TextView dateChoose, dateAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent login = getIntent();
        DocumentReference doc = FirebaseFirestore.getInstance().document("data/" + login.getStringExtra("user"));

        currYear = Calendar.getInstance().get(Calendar.YEAR);
        currMonth = Calendar.getInstance().get(Calendar.MONTH);
        currDate = Calendar.getInstance().get(Calendar.DATE);

        Intent intent = new Intent(this, LoginActivity.class);

        choseYear = currYear;
        choseMonth = currMonth;
        choseDate = currDate;

        addDialog = new Dialog(this);

        addButton = findViewById(R.id.button);
        overviewButton = findViewById(R.id.button2);
        logout = findViewById(R.id.button6);

        dateChoose = findViewById(R.id.textView4);
        String currTime = String.format("%d/%d/%d", currMonth + 1, currDate, currYear);
        dateChoose.setText(currTime);
        showItem(choseYear, choseMonth, choseDate);
        addButton.setOnClickListener(v -> {
            ShowAddPopup();
        });
        overviewButton.setOnClickListener(v -> {
            Intent overview = new Intent(this, OverviewActivity.class);
            overview.putExtra("size", data.size());
            for (int i = 0; i < data.size(); i++) {
                Store read = data.get(i);
                overview.putExtra("title" + i, read.getTitle());
                overview.putExtra("amount" + i, read.getAmount());
                overview.putExtra("type" + i, read.getType());
                overview.putExtra("detail" + i, read.getDetail());
                overview.putExtra("year" + i, read.getYear());
                overview.putExtra("month" + i, read.getMonth());
                overview.putExtra("date" + i, read.getDate());
            }
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
                    showItem(choseYear, choseMonth, choseDate);
                    dateChoose.setText(d);
                }
            }, currYear, currMonth, currDate);
            datePick.show();
        });
        logout.setOnClickListener(v -> {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(intent);
                    finish();
                }
            });
        });
    }
    public void ShowAddPopup() {
        setYear = currYear;
        setMonth = currMonth;
        setDay = currDate;

        addDialog.setContentView(R.layout.popup_add);

        typeAdd = addDialog.findViewById(R.id.spinner3);
        typeAdd.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new Type().types);
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
            Map<String, Store> save = new HashMap<String, Store>();
            String setTitle = titleAdd.getText().toString();
            double setAmount;
            try {
                setAmount = Double.valueOf(amountAdd.getText().toString());
            } catch (Exception e) {
                setAmount = 0.0;
            }
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
        for (int i = 0; i < data.size(); i++) {
            Store search = data.get(i);
            if (search.getYear() == year) {
                if (search.getMonth() == month) {
                    if (search.getDate() == date) {
                        empty = false;
                        none.setVisibility(View.GONE);
                        if (search.getType() > 0) {
                            spend.setVisibility(View.VISIBLE);
                            loadChunk(spending, search);
                        } else {
                            receive.setVisibility(View.VISIBLE);
                            loadChunk(receiving, search);
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

    public void loadChunk(LinearLayout toAdd, Store item) {
        View chunk = getLayoutInflater().inflate(R.layout.chunk_item, toAdd, false);
        TextView event = chunk.findViewById(R.id.event);
        TextView detail = chunk.findViewById(R.id.detail);
        TextView money = chunk.findViewById(R.id.money);
        event.setText(item.getTitle());
        detail.setText(item.getDetail());
        money.setText(String.valueOf(item.getAmount()));
        chunk.setOnClickListener(v -> {
            addDialog.setContentView(R.layout.popup_info);
            TextView title = addDialog.findViewById(R.id.textView5);
            TextView amount = addDialog.findViewById(R.id.textView11);
            TextView type = addDialog.findViewById(R.id.textView13);
            TextView description = addDialog.findViewById(R.id.textView15);
            Button delete = addDialog.findViewById(R.id.button4);
            title.setText(item.getTitle());
            amount.setText(String.valueOf(item.getAmount()));
            type.setText(new Type().getType(item.getType()));
            description.setText(item.getDetail());
            delete.setOnClickListener(v1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure to delete?");
                builder.setPositiveButton("YES", (unused1, unused2) -> {
                    data.remove(item);
                    addDialog.dismiss();
                    showItem(this.choseYear, this.choseMonth, this.choseDate);
                });
                builder.setNegativeButton("NO", null);
                builder.create().show();
            });

            addDialog.show();
        });
        toAdd.addView(chunk);
    }
}
