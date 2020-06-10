package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class EventsParticipated extends AppCompatActivity {

    ImageView eventPoster;
    TextView eventTitle, eventDescription, eventLocation, eventVenue;
    Button participantList, getFixture, getResult, raiseQuery;
    CreateEvent createEvent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_participated);

        extractView();

        Picasso.with(this).load(createEvent.getUrlLink()).into(eventPoster);
        eventTitle.setText(createEvent.getEventName());
        eventDescription.setText(createEvent.getEventDescription());
        eventLocation.setText("Location : "+createEvent.getField_Name()+", "+createEvent.getCity_Name()+", "+createEvent.getPostal_Code());
        eventVenue.setText("Venue : "+createEvent.getEt_Date()+"    "+createEvent.getChoose_Time());

    }

    public void extractView(){

        participantList=findViewById(R.id.get_my_participant_list);
        getFixture=findViewById(R.id.get_my_fixture);
        getResult=findViewById(R.id.get_my_result);
        raiseQuery=findViewById(R.id.my_raise_query);
        eventPoster = findViewById(R.id.participant_my_event_created_poster);
        eventTitle = findViewById(R.id.participant_my_event_created_title);
        eventDescription = findViewById(R.id.participant_my_event_created_detail);
        eventVenue = findViewById(R.id.participant_my_event_created_venue);
        eventLocation = findViewById(R.id.participant_my_event_created_location);
        createEvent = (CreateEvent)getIntent().getSerializableExtra("My Event Participated Detail");

    }

}
