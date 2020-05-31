package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CreateEvent1 extends AppCompatActivity {
    TextView tvDate;
    EditText etDate;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;

    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    DatePickerDialog.OnDateSetListener setListener;

    //EditText et;
    //Button bt;
    //ListView lv;
    //ArrayList<String> arrayList;
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);

        // et = findViewById(R.id.editText);
        //bt = findViewById(R.id.button_addData);
       // lv = findViewById(R.id.listView_lv);

        //arrayList = new ArrayList<String>();
        //adapter = new ArrayAdapter<String>(CreateEvent1.this, android.R.layout.simple_list_item_1,
          //      arrayList);
        //lv.setAdapter(adapter);

        //bt.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  String names=et.getText().toString();
                //arrayList.add(names);
                //lv.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            //}
        //});

        // onBtnClick();

    chooseTime =findViewById(R.id.etChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(CreateEvent1.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                //chooseTime.setText(hourOfDay + ":" + minutes + amPm);
            }
        }, currentHour, currentMinute, false);
        timePickerDialog.show();
    }
    });


    tvDate = findViewById(R.id.tv_date);

    etDate = findViewById(R.id.et_date);

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                CreateEvent1.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                setListener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    });

    setListener =new DatePickerDialog.OnDateSetListener()

    {
        @Override
        public void onDateSet (DatePicker view,int year, int month, int dayOfMonth){
        month = month + 1;
        String date = day + "/" + month + "/" + year;
        tvDate.setText(date);
    }
    };

        etDate.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                CreateEvent1.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                etDate.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    });
}

   // public void onBtnClick(){
     //   bt.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {
           //     String result=et.getText().toString();
             //   arrayList.add(result);
               // adapter.notifyDataSetChanged();
            //}
        // });
    // }


        public void intentCall (View view){
            Intent homeIntent = new Intent(CreateEvent1.this, CreateEvent2.class);
            startActivity(homeIntent);
        }
}



