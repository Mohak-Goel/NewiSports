package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button createEvent;

    private HomePageAdapter homePageAdapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        createEvent = findViewById(R.id.create_event);
        recyclerView = findViewById(R.id.home_page_event_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, CreateEvent1.class));
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Event Details");

        FirebaseRecyclerOptions<CreateEvent> options = new FirebaseRecyclerOptions.Builder<CreateEvent>().setQuery(ref, CreateEvent.class).build();

        homePageAdapter = new HomePageAdapter(HomePage.this, options);

        recyclerView.setAdapter(homePageAdapter);

        homePageAdapter.setOnClickListener(new HomePageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

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

            case R.id.nav_hostedevents: startActivity(new Intent(HomePage.this, MyEventsCreatedTotal.class)); break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        homePageAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        homePageAdapter.stopListening();
    }
}
