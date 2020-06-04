package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*------------------------Hooks-----------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        /*-------------------------ToolBar--------------------*/
        setSupportActionBar(toolbar);
        /*---------------------Navigation Drawer Menu--------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_account:
                Intent intent = new Intent(HomePage.this, MyAccount.class);
                startActivity(intent);
                break;

            case R.id.nav_events:
                Intent intent1 = new Intent(HomePage.this, MyEvents.class);
                startActivity(intent1);
                break;

            case R.id.nav_rquery:
                Intent intent2 = new Intent(HomePage.this, RaiseQuery.class);
                startActivity(intent2);
                break;
            case R.id.nav_gquery:
                Intent intent7 = new Intent(HomePage.this, GetQuery.class);
                startActivity(intent7);
                break;

            case R.id.nav_notice:
                Intent intent3 = new Intent(HomePage.this, Notice.class);
                startActivity(intent3);
                break;

            case R.id.nav_unotice:
                Intent intent4 = new Intent(HomePage.this, UploadNotice.class);
                startActivity(intent4);
                break;

            case R.id.nav_contact:
                Intent intent5 = new Intent(HomePage.this, Contact.class);
                startActivity(intent5);
                break;

            case R.id.nav_logout:
                Intent intent6 = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent6);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void createEvent(View view){
        Intent intent8 = new Intent(HomePage.this,CreateEvent1.class);
        startActivity(intent8);
    }
    public void participateEvent(View view){
        Intent intent9 = new Intent(HomePage.this,ParticipationForm.class);
        startActivity(intent9);
    }
    public void participateEvent1(View view){
        Intent intent10 = new Intent(HomePage.this,ParticipationForm.class);
        startActivity(intent10);
    }
    public void participateEvent2(View view){
        Intent intent11 = new Intent(HomePage.this,ParticipationForm.class);
        startActivity(intent11);
    }
    public void participateEvent3(View view){
        Intent intent12 = new Intent(HomePage.this,ParticipationForm.class);
        startActivity(intent12);
    }
    public void participateEvent4(View view){
        Intent intent13 = new Intent(HomePage.this,ParticipationForm.class);
        startActivity(intent13);
    }

}

