package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CreateEvent1 extends AppCompatActivity {
    TextView tvDate;
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);

        tvDate=findViewById(R.id.tv_date);
        etDate=findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        CreateEvent1.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
                }

        });

       setListener=new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=day+"/"+month+"/"+year;
                tvDate.setText(date);
           }
       };
       etDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               DatePickerDialog datePickerDialog=new DatePickerDialog(
                       CreateEvent1.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int day) {
                       month=month+1;
                       String date=day+"/"+month+"/"+year;
                       etDate.setText(date);
                   }
               },year,month,day);
               datePickerDialog.show();
           }
       });
    }
    public void intentCall(View view){
        Intent homeIntent=new Intent(CreateEvent1.this,CreateEvent2.class);
        startActivity(homeIntent);
    }


}
