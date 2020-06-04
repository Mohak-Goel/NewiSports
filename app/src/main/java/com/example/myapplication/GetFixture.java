package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetFixture extends AppCompatActivity {

    private RecyclerView getFixtureList;
    private RecyclerView.LayoutManager mLayoutManager;
    private GetFixtureAdapter mAdapter;

    ArrayList<FixtureDetail> fixtureDetailList;

    DatabaseReference fixtureDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_fixture);

        getFixtureList = (RecyclerView)findViewById(R.id.get_fixture_list);
        fixtureDetailList = new ArrayList<>();
        getFixtureList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GetFixtureAdapter(fixtureDetailList);
        getFixtureList.setLayoutManager(mLayoutManager);
        getFixtureList.setAdapter(mAdapter);
        fixtureDatabase = FirebaseDatabase.getInstance().getReference("Fixture Details");

        fixtureDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot fixtureSnapshot: dataSnapshot.getChildren()){
                    FixtureDetail fixtureDetail = fixtureSnapshot.getValue(FixtureDetail.class);
                    fixtureDetailList.add(new FixtureDetail(fixtureDetail.getParticipant1(), fixtureDetail.getParticipant2(), fixtureDetail.getFixtureDate(), fixtureDetail.getFixtureTime()));
                    mAdapter.notifyItemInserted(fixtureDetailList.size()-1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
