package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MyEventsCreated extends AppCompatActivity {

    ImageView eventPoster;
    TextView eventTitle, eventDescription, eventLocation, eventVenue;
    Button finishButton, participantList, getFixture, uploadFixture, uploadResult, getResult, raiseQuery;
    CreateEvent createEvent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_created);

        extractViews();


        Picasso.with(this).load(createEvent.getUrlLink()).into(eventPoster);
        eventTitle.setText(createEvent.getEventName());
        eventDescription.setText(createEvent.getEventDescription());
        eventLocation.setText("Location : "+createEvent.getField_Name()+", "+createEvent.getCity_Name()+", "+createEvent.getPostal_Code());
        eventVenue.setText("Venue : "+createEvent.getEt_Date()+"    "+createEvent.getChoose_Time());


    }

    public void extractViews(){

        participantList=findViewById(R.id.get_participant_list);
        getFixture=findViewById(R.id.get_fixture);
        uploadFixture=findViewById(R.id.upload_fixture);
        uploadResult=findViewById(R.id.upload_result);
        getResult=findViewById(R.id.get_result);
        raiseQuery=findViewById(R.id.raise_query);
        finishButton = findViewById(R.id.finish_button);
        eventPoster = findViewById(R.id.my_event_created_poster);
        eventTitle = findViewById(R.id.my_event_created_title);
        eventDescription = findViewById(R.id.my_event_created_detail);
        eventVenue = findViewById(R.id.my_event_created_venue);
        eventLocation = findViewById(R.id.my_event_created_location);
        createEvent = (CreateEvent)getIntent().getSerializableExtra("Event Created Details Adapter");

    }



}
