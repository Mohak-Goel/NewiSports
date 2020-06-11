package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


    EditText EventName, FieldLocation, CityName, PostalCode, SportsName;
    Button ClickNext;

    FirebaseDatabase rootNode;
     DatabaseReference reference;


    //EditText et;
    //Button bt;
    //ListView lv;
    //ArrayList<String> arrayList;
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);

        EventName = findViewById(R.id.eventName);
        FieldLocation = findViewById(R.id.fieldLoc);
        CityName = findViewById(R.id.cityName);
        PostalCode = findViewById(R.id.postalCode);
        SportsName = findViewById(R.id.sportsName);
        ClickNext = findViewById(R.id.clickNext);


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

        chooseTime = findViewById(R.id.etChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Calendar calendar = Calendar.getInstance();
                                              final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                              final int min = calendar.get(Calendar.MINUTE);
                                              TimePickerDialog mTimePicker;
                                              mTimePicker = new TimePickerDialog(CreateEvent1.this, new TimePickerDialog.OnTimeSetListener() {
                                                  @Override
                                                  public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                                                      String amPm = "";
                                                      if (selectedHour > 12 && selectedHour!=12) {
                                                          selectedHour-=12;
                                                          amPm = "PM";
                                                      } else {
                                                          if (selectedHour==12)
                                                              amPm = "PM";
                                                          else if (selectedHour==0){
                                                              selectedHour = 12;
                                                              amPm = "AM";
                                                          }
                                                          else
                                                              amPm = "AM";

                                                      }

                                                      chooseTime.setText( String.format("%02d:%02d", selectedHour, selectedMinute) + " "+amPm);
                                                  }
                                              }, hour, min, false);
                                              mTimePicker.setTitle("Select Start Time");
                                              mTimePicker.show();

                                          }
            });

              //  timePickerDialog = new TimePickerDialog(CreateEvent1.this, new TimePickerDialog.OnTimeSetListener() {

                //    @Override
                  //  public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    //    if (hourOfDay >= 12) {
                      //      amPm = "PM";
                        //} else {
                          //  amPm = "AM";
                        //}
                        //chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        //chooseTime.setText(hourOfDay + ":" + minutes + amPm);
                    //}
                //}, currentHour, currentMinute, false);
                //timePickerDialog.show();






        etDate = findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        ClickNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // rootNode = FirebaseDatabase.getInstance();
               // reference = rootNode.getReference("Event Details");

                CreateEvent1Form e = new CreateEvent1Form(EventName.getText().toString(), FieldLocation.getText().toString(),
                        CityName.getText().toString(), PostalCode.getText().toString(), SportsName.getText().toString(),
                        chooseTime.getText().toString(), etDate.getText().toString());

                // reference.setValue(e);
                intentCall();
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


    public void intentCall() {
        CreateEvent1Form e=new CreateEvent1Form(EventName.getText().toString(), FieldLocation.getText().toString(),
             CityName.getText().toString(), PostalCode.getText().toString(), SportsName.getText().toString(),
                chooseTime.getText().toString(), etDate.getText().toString());
        Intent homeIntent = new Intent(CreateEvent1.this, CreateEvent2.class);
        homeIntent.putExtra("CreateEvent1 Data", e);
        startActivity(homeIntent);
        finish();
    }

}


