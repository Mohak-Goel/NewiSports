package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EventsParticipated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_participated);

        Button button1=findViewById(R.id.participants_list);
        Button button2=findViewById(R.id.fixture_details);
        Button button3=findViewById(R.id.result_published);
        Button button4=findViewById(R.id.any_queries);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EventsParticipated.this,"Get Participants Details",Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EventsParticipated.this,"Get Fixture Details",Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EventsParticipated.this,"Get Results",Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Toast.makeText(EventsParticipated.this,"Get Queries",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
