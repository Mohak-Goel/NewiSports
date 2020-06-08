package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RaiseQuery extends AppCompatActivity {
    EditText E1,E2,E3;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_query);
        E1=findViewById(R.id.e1);
        E2=findViewById(R.id.e2);
        E3=findViewById(R.id.e3);
        send=findViewById(R.id.b1);
    }

    public void sendgmail(View v)
    {
        String to=E1.getText().toString();
        String subject=E2.getText().toString();
        String message=E3.getText().toString();
        Intent mailIntent=new Intent(Intent.ACTION_SEND);
        mailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT,message);
        mailIntent.setType("message/plain");
        startActivity(Intent.createChooser(mailIntent,"chose the mail clint"));

    }
}
