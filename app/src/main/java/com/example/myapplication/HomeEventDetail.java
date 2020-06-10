package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeEventDetail extends AppCompatActivity {

    ImageView eventPoster;
    TextView eventTitle, eventDescription, eventLocation, eventVenue;
    Button participate;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_event_detail);

        eventPoster=findViewById(R.id.event_poster);
        eventTitle=findViewById(R.id.event_title);
        eventDescription=findViewById(R.id.event_detail);
        eventLocation=findViewById(R.id.event_location);
        eventVenue=findViewById(R.id.event_venue);
        participate = findViewById(R.id.event_participation_button);

        final CreateEvent createEvent = (CreateEvent) getIntent().getSerializableExtra("event detail");

        Picasso.with(this).load(createEvent.getUrlLink()).into(eventPoster);
        eventTitle.setText(createEvent.getEventName());
        eventDescription.setText(createEvent.getEventDescription());
        eventLocation.setText("Location : "+createEvent.getField_Name()+", "+createEvent.getCity_Name()+", "+createEvent.getPostal_Code());
        eventVenue.setText("Venue : "+createEvent.getEt_Date()+"    "+createEvent.getChoose_Time());


        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            CreateEvent createEvent1 = childSnapshot.getValue(CreateEvent.class);

                            Intent intent = new Intent(HomeEventDetail.this, ParticipationForm.class);

                            intent.putExtra("Home Event Detail", createEvent1);
                            intent.putExtra("Event Detail Key", childSnapshot.getKey());

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
}