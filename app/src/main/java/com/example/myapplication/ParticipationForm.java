package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ParticipationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_form);
    }

    public void iCall(View view){
        Intent i1 = new Intent(ParticipationForm.this, ParticipantsDetail.class);
        startActivity(i1);
    }

}
