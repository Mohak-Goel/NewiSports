package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyEventsCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_created);

    Button button1=findViewById(R.id.get_participants);
    Button button2=findViewById(R.id.get_fixture);
    Button button3=findViewById(R.id.upload_fixture);
    Button button4=findViewById(R.id.upload_result);
    Button button5=findViewById(R.id.get_result);
    Button button6=findViewById(R.id.raise_queries);

    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MyEventsCreated.this,"Get Participants List",Toast.LENGTH_SHORT).show();
        }
    });
    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MyEventsCreated.this,"Get Fixture Details",Toast.LENGTH_SHORT).show();
        }
    });
    button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MyEventsCreated.this,"Upload Fixture Details",Toast.LENGTH_SHORT).show();
        }
    });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MyEventsCreated.this,"Upload Event Result",Toast.LENGTH_SHORT).show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MyEventsCreated.this,"Get Event Result",Toast.LENGTH_SHORT).show();
            }
        });
        button6 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MyEventsCreated.this,"Raise Queries",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
