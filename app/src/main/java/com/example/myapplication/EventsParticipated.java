package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EventsParticipated extends AppCompatActivity {

    ImageView eventPoster;
    TextView eventTitle, eventDescription, eventLocation, eventVenue, phone;
    RelativeLayout food, lodge, transport;
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

        if (!createEvent.isFood())
            food.setVisibility(View.GONE);

        if (!createEvent.isTransport())
            transport.setVisibility(View.GONE);

        if (!createEvent.isLodging())
            lodge.setVisibility(View.GONE);

        phone.setText((createEvent.getOurContact().startsWith("+91")?createEvent.getOurContact():"+91"+createEvent.getOurContact()));


        participantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {


                            Intent intent = new Intent(EventsParticipated.this, ParticipantList.class);

                            intent.putExtra("ED Key", childSnapshot.getKey());

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        getFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            Intent intent = new Intent(EventsParticipated.this, GetFixture.class);

                            intent.putExtra("ED Key", childSnapshot.getKey());

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        getResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            Intent intent = new Intent(EventsParticipated.this, GetResult.class);

                            intent.putExtra("ED Key3", childSnapshot.getKey());

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        raiseQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            Intent intent = new Intent(EventsParticipated.this, RaiseQuery.class);

                            intent.putExtra("ED Key", childSnapshot.getKey());

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    public void extractView(){

        participantList=findViewById(R.id.get_my_participant_list);
        getFixture=findViewById(R.id.get_my_fixture);
        getResult=findViewById(R.id.get_my_result);
        raiseQuery=findViewById(R.id.my_raise_query);
        food = findViewById(R.id.participated_my_event_created_food);
        lodge = findViewById(R.id.participated_my_event_created_lodge);
        transport = findViewById(R.id.participated_my_event_created_transport);
        phone = findViewById(R.id.participated_my_event_created_contact_no);
        eventPoster = findViewById(R.id.participant_my_event_created_poster);
        eventTitle = findViewById(R.id.participant_my_event_created_title);
        eventDescription = findViewById(R.id.participant_my_event_created_detail);
        eventVenue = findViewById(R.id.participant_my_event_created_venue);
        eventLocation = findViewById(R.id.participant_my_event_created_location);
        createEvent = (CreateEvent)getIntent().getSerializableExtra("My Event Participated Detail");

    }

}
