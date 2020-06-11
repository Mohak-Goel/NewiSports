package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button createEvent;
    DatabaseReference reff;
    FirebaseUser user_a;
    String uid_a;

    Button upcoming, live, previous;

    private HomePageAdapter homePageAdapter;
    private RecyclerView recyclerView;

    ArrayList<CreateEvent> eventArrayList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        user_a= FirebaseAuth.getInstance().getCurrentUser();
        assert user_a != null;
        uid_a=user_a.getUid();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        upcoming = findViewById(R.id.upcoming_button);
        live = findViewById(R.id.live_button);
        previous = findViewById(R.id.previous_button);
        toolbar = findViewById(R.id.toolbar);
        eventArrayList = new ArrayList<>();
        createEvent = findViewById(R.id.create_event);
        recyclerView = (RecyclerView) findViewById(R.id.home_page_event_list);
        homePageAdapter = new HomePageAdapter(HomePage.this, eventArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(homePageAdapter);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final Menu menu = navigationView.getMenu();

        reff= FirebaseDatabase.getInstance().getReference().child("Registration Details").child(uid_a);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {

                    String user = Objects.requireNonNull(dataSnapshot.child("UserType").getValue()).toString();

                    if (user.equalsIgnoreCase("PARTICIPANT"))
                    {
                        menu.findItem(R.id.nav_hostedevents).setVisible(false);
                        menu.findItem(R.id.nav_unotice).setVisible(false);
                    }

                    else if (user.equalsIgnoreCase("OPERATIONAL MANAGER"))
                    {
                        menu.findItem(R.id.nav_participatedevents).setVisible(false);
                    }

                    else if (user.equalsIgnoreCase("HOST"))
                    {
                        menu.findItem(R.id.nav_unotice).setVisible(false);
                        createEvent.setVisibility(View.VISIBLE);
                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(HomePage.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, CreateEvent1.class));
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

      /*  DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

        eventArrayList.clear();
        homePageAdapter.notifyDataSetChanged();

        ref.orderByChild("status").equalTo("Upcoming").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    CreateEvent event = snapshot.getValue(CreateEvent.class);
                    assert event != null;
                    eventArrayList.add(new CreateEvent(event.getEventName(),event.getField_Name(), event.getCity_Name(), event.getPostal_Code(), event.getSports_Name(), event.getChoose_Time(), event.getEt_Date(), event.isFood(), event.isLodging(), event.isTransport(), event.getEventDescription(), event.getOurContact(), event.getUrlLink(), event.getStatus()));
                    homePageAdapter.notifyItemInserted(eventArrayList.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                live.setBackgroundColor(Color.TRANSPARENT);
                previous.setBackgroundColor(Color.TRANSPARENT);
                upcoming.setBackgroundColor(Color.rgb(139,195,74));

                eventArrayList.clear();
                homePageAdapter.notifyDataSetChanged();

                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("status").equalTo("Upcoming").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            CreateEvent event = snapshot.getValue(CreateEvent.class);
                            assert event != null;
                            eventArrayList.add(new CreateEvent(event.getEventName(),event.getField_Name(), event.getCity_Name(), event.getPostal_Code(), event.getSports_Name(), event.getChoose_Time(), event.getEt_Date(), event.isFood(), event.isLodging(), event.isTransport(), event.getEventDescription(), event.getOurContact(), event.getUrlLink(), event.getStatus()));
                            homePageAdapter.notifyItemInserted(eventArrayList.size()-1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upcoming.setBackgroundColor(Color.TRANSPARENT);
                previous.setBackgroundColor(Color.TRANSPARENT);
                live.setBackgroundColor(Color.rgb(255,235,59));

                eventArrayList.clear();
                homePageAdapter.notifyDataSetChanged();

                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("status").equalTo("Live").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            CreateEvent event = snapshot.getValue(CreateEvent.class);
                            assert event != null;
                            eventArrayList.add(new CreateEvent(event.getEventName(),event.getField_Name(), event.getCity_Name(), event.getPostal_Code(), event.getSports_Name(), event.getChoose_Time(), event.getEt_Date(), event.isFood(), event.isLodging(), event.isTransport(), event.getEventDescription(), event.getOurContact(), event.getUrlLink(), event.getStatus()));
                            homePageAdapter.notifyItemInserted(eventArrayList.size()-1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                live.setBackgroundColor(Color.TRANSPARENT);
                upcoming.setBackgroundColor(Color.TRANSPARENT);
                previous.setBackgroundColor(Color.rgb(244,67,54));

                eventArrayList.clear();
                homePageAdapter.notifyDataSetChanged();

                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

                ref.orderByChild("status").equalTo("Finished").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            CreateEvent event = snapshot.getValue(CreateEvent.class);
                            assert event != null;
                            eventArrayList.add(new CreateEvent(event.getEventName(),event.getField_Name(), event.getCity_Name(), event.getPostal_Code(), event.getSports_Name(), event.getChoose_Time(), event.getEt_Date(), event.isFood(), event.isLodging(), event.isTransport(), event.getEventDescription(), event.getOurContact(), event.getUrlLink(), event.getStatus()));
                            homePageAdapter.notifyItemInserted(eventArrayList.size()-1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                break;

            case R.id.nav_account:
                startActivity(new Intent(HomePage.this, MyAccount.class));
                break;

            case R.id.nav_unotice:
                startActivity(new Intent(HomePage.this, UploadNotice.class));
                break;

            case R.id.nav_notice: startActivity(new Intent(HomePage.this, GetNotice.class));
                break;

            case R.id.nav_contact:
                startActivity(new Intent(HomePage.this, Contact.class)); break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(HomePage.this, MainActivity.class));
                break;

            case R.id.nav_participatedevents:
                startActivity(new Intent(HomePage.this,EventsParticipatedTotal.class));
                break;

            case R.id.nav_hostedevents:
                eventArrayList.clear();
                homePageAdapter.notifyDataSetChanged();
                startActivity(new Intent(HomePage.this, MyEventsCreatedTotal.class)); break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void xyz(View view){
        live.setBackgroundColor(Color.TRANSPARENT);
        previous.setBackgroundColor(Color.TRANSPARENT);
        upcoming.setBackgroundColor(Color.rgb(139,195,74));

        eventArrayList.clear();
        homePageAdapter.notifyDataSetChanged();

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

        ref.orderByChild("status").equalTo("Upcoming").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    CreateEvent event = snapshot.getValue(CreateEvent.class);
                    assert event != null;
                    eventArrayList.add(new CreateEvent(event.getEventName(),event.getField_Name(), event.getCity_Name(), event.getPostal_Code(), event.getSports_Name(), event.getChoose_Time(), event.getEt_Date(), event.isFood(), event.isLodging(), event.isTransport(), event.getEventDescription(), event.getOurContact(), event.getUrlLink(), event.getStatus()));
                    homePageAdapter.notifyItemInserted(eventArrayList.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventArrayList.clear();

    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setCheckedItem(R.id.nav_home);
        upcoming.callOnClick();
    }
}
