package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEvent1 extends AppCompatActivity {
    EditText etDate, chooseTime, EventName, FieldLocation, CityName, PostalCode, SportsName;
    Button ClickNext;

    Toast toast;

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
        chooseTime = findViewById(R.id.etChooseTime);

        toast = Toast.makeText(getApplicationContext(), "Kindly Fill the Form", Toast.LENGTH_SHORT);
        toast.show();

        chooseTime.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Calendar calendar = Calendar.getInstance();
                                              final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                              final int min = calendar.get(Calendar.MINUTE);
                                              TimePickerDialog mTimePicker;
                                              mTimePicker = new TimePickerDialog(CreateEvent1.this, new TimePickerDialog.OnTimeSetListener() {
                                                  @SuppressLint({"DefaultLocale", "SetTextI18n"})
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
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        ClickNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(EventName.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Enter event name!!", Toast.LENGTH_SHORT);
                    toast.show();

                }

                else if(FieldLocation.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Enter Location!!", Toast.LENGTH_SHORT);
                    toast.show();

                }

                else if(CityName.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Enter City Name!!", Toast.LENGTH_SHORT);
                    toast.show();

                }


                else if(PostalCode.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Enter Postal Code!!", Toast.LENGTH_SHORT);
                    toast.show();

                }


                else if(SportsName.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Enter Sports Name!!", Toast.LENGTH_SHORT);
                    toast.show();

                }


                else if(chooseTime.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Choose Time!!", Toast.LENGTH_SHORT);
                    toast.show();

                }


                else if(etDate.getText().toString().isEmpty()){

                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Choose Date!!", Toast.LENGTH_SHORT);
                    toast.show();

                }

                else if (!isPostalCodeValid(PostalCode.getText().toString()))
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Invalid Postal code!!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else  intentCall();
            }
        });

    }

    public void intentCall() {
        CreateEvent1Form e=new CreateEvent1Form(EventName.getText().toString(), FieldLocation.getText().toString(),
             CityName.getText().toString(), PostalCode.getText().toString(), SportsName.getText().toString(),
                chooseTime.getText().toString(), etDate.getText().toString());
        Intent homeIntent = new Intent(CreateEvent1.this, CreateEvent2.class);
        homeIntent.putExtra("CreateEvent1 Data", e);
        startActivity(homeIntent);
        finish();
    }

    public static boolean isPostalCodeValid(String pcode) {
        boolean isValid = false;

        String expression = "^[1-9][0-9]{5}$";
        CharSequence inputStr = pcode;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}


