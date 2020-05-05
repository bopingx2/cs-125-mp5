package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Dialog addDialog;
    private Button addButton, overviewButton, done;
    private EditText dateActivity, dateAdd, titleAdd, amountAdd, descriptionAdd;
    private Spinner typeAdd;
    private List<Store> data = new ArrayList<>();

    private DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDialog = new Dialog(this);
        addButton = findViewById(R.id.button);
        overviewButton = findViewById(R.id.button2);
        dateActivity = findViewById(R.id.editText);
        addButton.setOnClickListener(v -> {
            ShowAddPopup();
        });
        overviewButton.setOnClickListener(v -> {
            Intent overview = new Intent(this, OverviewActivity.class);
            startActivity(overview);
        });
        dateActivity.setOnClickListener(v -> {

        });


    }
    public void ShowAddPopup() {
        addDialog.setContentView(R.layout.popup_add);
        done = addDialog.findViewById(R.id.button3);
        dateAdd = addDialog.findViewById(R.id.editText6);
        titleAdd = addDialog.findViewById(R.id.editText2);
        amountAdd = addDialog.findViewById(R.id.editText7);
        descriptionAdd = addDialog.findViewById(R.id.editText5);
        typeAdd = addDialog.findViewById(R.id.spinner3);
        addDialog.show();
        done.setOnClickListener(v -> {

            addDialog.dismiss();
        });
    }
}
