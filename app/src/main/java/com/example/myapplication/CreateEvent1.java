package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateEvent1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
    }
    public void intentCall(View view){
        Intent homeIntent=new Intent(CreateEvent1.this,CreateEvent2.class);
        startActivity(homeIntent);
    }
}
