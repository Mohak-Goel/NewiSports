package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView eventTitle, eventDescription, eventLocation, eventVenue, contact;
    Button participate;

    RelativeLayout food, lodge, transport;
    Toast toast;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_event_detail);

        eventPoster=findViewById(R.id.event_poster);
        eventTitle=findViewById(R.id.event_title);
        eventDescription=findViewById(R.id.event_detail);
        eventLocation=findViewById(R.id.home_location);
        eventVenue=findViewById(R.id.home_venue);
        participate = findViewById(R.id.event_participation_button);
        contact = findViewById(R.id.home_contact_no);
        food = findViewById(R.id.home_food);
        lodge = findViewById(R.id.home_lodge);
        transport = findViewById(R.id.home_transport);

        final CreateEvent createEvent = (CreateEvent) getIntent().getSerializableExtra("event detail");

        Picasso.with(this).load(createEvent.getUrlLink()).into(eventPoster);
        eventTitle.setText(createEvent.getEventName());
        eventDescription.setText(createEvent.getEventDescription());
        eventLocation.setText(createEvent.getField_Name()+", "+createEvent.getCity_Name()+", "+createEvent.getPostal_Code());
        eventVenue.setText(createEvent.getEt_Date()+"    "+createEvent.getChoose_Time());

        if(!createEvent.isTransport())
            transport.setVisibility(View.GONE);

        if (!createEvent.isFood())
            food.setVisibility(View.GONE);

        if (!createEvent.isLodging())
            lodge.setVisibility(View.GONE);

        if (createEvent.getStatus().equalsIgnoreCase("Live") || createEvent.getStatus().equalsIgnoreCase("Finished")) {

            participate.setTextColor(Color.GRAY);

            toast= Toast.makeText(getApplicationContext(), "Sorry! Participation is Closed!", Toast.LENGTH_SHORT);

            toast.show();
        }

        contact.setText((createEvent.getOurContact().startsWith("+91")?createEvent.getOurContact():"+91"+createEvent.getOurContact()));

        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            CreateEvent createEvent1 = childSnapshot.getValue(CreateEvent.class);

                            if (createEvent.getStatus().equalsIgnoreCase("Live") || createEvent.getStatus().equalsIgnoreCase("Finished")) {

                                toast.cancel();
                                toast= Toast.makeText(getApplicationContext(), "Sorry! Participation is Closed!", Toast.LENGTH_SHORT);

                                toast.show();
                            }
                            else {
                                participate.setTextColor(Color.BLACK);
                                Intent intent = new Intent(HomeEventDetail.this, ParticipationForm.class);

                                intent.putExtra("Home Event Detail", createEvent1);
                                intent.putExtra("Event Detail Key", childSnapshot.getKey());

                                startActivity(intent);
                            }
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