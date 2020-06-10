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

        uploadFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {


                            Intent intent = new Intent(MyEventsCreated.this, UploadFixture.class);

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

                            Intent intent = new Intent(MyEventsCreated.this, GetFixture.class);

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

        uploadResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            Intent intent = new Intent(MyEventsCreated.this, UploadResult.class);

                            intent.putExtra("ED Key2", childSnapshot.getKey());

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

                            Intent intent = new Intent(MyEventsCreated.this, RaiseQuery.class);

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

                            Intent intent = new Intent(MyEventsCreated.this, GetResult.class);

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

        participantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {


                            Intent intent = new Intent(MyEventsCreated.this, ParticipantList.class);

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

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
