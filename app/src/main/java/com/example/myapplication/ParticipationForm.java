package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ParticipationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_form);
    }

    public void intentCall(View view){
        Intent homeIntent = new Intent(ParticipationForm.this, ParticipantsDetail.class);
        startActivity(homeIntent);
    }
}
