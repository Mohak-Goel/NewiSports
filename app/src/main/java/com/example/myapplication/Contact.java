package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toast.makeText(Contact.this, "Please Wait...", Toast.LENGTH_SHORT).show();
    }
    public void back(View v)
    {
        startActivity(new Intent(Contact.this,HomePage.class));
    }
}
