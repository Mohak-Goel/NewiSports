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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MyEventsCreated extends AppCompatActivity {

    ImageView eventPoster;
    TextView eventTitle, eventDescription, contact,eventLocation, eventVenue;
    RelativeLayout food, lodge, transport;
    Button participantList, getFixture, uploadFixture, uploadResult, getResult, raiseQuery, button;
    CreateEvent createEvent;

    FirebaseUser user_a;
    String uid_a;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_created);

        extractViews();


        Picasso.with(this).load(createEvent.getUrlLink()).into(eventPoster);
        eventTitle.setText(createEvent.getEventName());
        eventDescription.setText(createEvent.getEventDescription());

        if (!createEvent.isFood())
            food.setVisibility(View.GONE);

        if (!createEvent.isTransport())
            transport.setVisibility(View.GONE);

        if (!createEvent.isLodging())
            lodge.setVisibility(View.GONE);

        contact.setText((createEvent.getOurContact().startsWith("+91")?createEvent.getOurContact():"+91"+createEvent.getOurContact()));
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

        DatabaseReference refff = FirebaseDatabase.getInstance().getReference("Event Details");

        refff.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    assert key != null;
                    DatabaseReference r =FirebaseDatabase.getInstance().getReference("Event Details").child(key);
                    r.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String status = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();
                            if(status.equalsIgnoreCase("Upcoming")) {
                                button.setText("Start Event");
                                button.setTextColor(Color.BLACK);
                            }

                            else if (status.equalsIgnoreCase("Live")) {
                                button.setText("Finish Event");
                                button.setTextColor(Color.BLACK);
                            }

                            else {
                                button.setText("Finished");
                                button.setTextColor(Color.GRAY);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("ourContact").equalTo(createEvent.getOurContact()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                            String key = childSnapshot.getKey();
                            if (button.getText().toString().equalsIgnoreCase("Start Event")) {
                                assert key != null;
                                Toast.makeText(getApplicationContext(), button.getText().toString(), Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference("Event Details").child(key).child("status").setValue("Live");
                                FirebaseDatabase.getInstance().getReference("My Events Created").child(uid_a).child(key).child("status").setValue("Live");
                                button.setText("Finish Event");
                                button.setTextColor(Color.BLACK);
                            } else if (button.getText().toString().equalsIgnoreCase("Finish Event")) {
                                assert key != null;
                                Toast.makeText(getApplicationContext(), button.getText().toString(), Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference("My Events Created").child(uid_a).child(key).child("status").setValue("Live");
                                FirebaseDatabase.getInstance().getReference("Event Details").child(key).child("status").setValue("Finished");
                                button.setText("Finished");
                                button.setTextColor(Color.GRAY);
                            } else {
                                Toast.makeText(getApplicationContext(), "Event Finished", Toast.LENGTH_SHORT).show();
                                button.setTextColor(Color.GRAY);
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

    public void extractViews(){

        participantList=findViewById(R.id.get_participant_list);
        getFixture=findViewById(R.id.get_fixture);
        uploadFixture=findViewById(R.id.upload_fixture);
        food = findViewById(R.id.my_event_created_food);
        lodge = findViewById(R.id.my_event_created_lodge);
        transport = findViewById(R.id.my_event_created_transport);
        contact = findViewById(R.id.my_event_created_contact_no);
        uploadResult=findViewById(R.id.upload_result);
        getResult=findViewById(R.id.get_result);
        raiseQuery=findViewById(R.id.raise_query);
        eventPoster = findViewById(R.id.my_event_created_poster);
        eventTitle = findViewById(R.id.my_event_created_title);
        eventDescription = findViewById(R.id.my_event_created_detail);
        button = findViewById(R.id.start_event_button);
        eventVenue = findViewById(R.id.my_event_created_venue);
        eventLocation = findViewById(R.id.my_event_created_location);
        createEvent = (CreateEvent)getIntent().getSerializableExtra("Event Created Details Adapter");
        user_a= FirebaseAuth.getInstance().getCurrentUser();
        assert user_a != null;
        uid_a=user_a.getUid();
    }



}
