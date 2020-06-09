package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsParticipatedTotal extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EventsParticipatedTotalAdapter mAdapter;
    List<String> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_participated_total);

        mRecyclerView=findViewById(R.id.events_participated_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        pList=new ArrayList<>();

        FirebaseUser user_id= FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        String uid_user=user_id.getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("My Events Participated").child(uid_user);



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    ParticipantUniversity puniv=snapshot.getValue(ParticipantUniversity.class);
                    String eId=puniv.getEventid();
                    pList.add(eId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference ref2=FirebaseDatabase.getInstance().getReference("Event Details");
        FirebaseRecyclerOptions<CreateEvent> options = new FirebaseRecyclerOptions.Builder<CreateEvent>().setQuery(ref, CreateEvent.class).build();

            mAdapter = new EventsParticipatedTotalAdapter(EventsParticipatedTotal.this, options);

            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnClickListener(new EventsParticipatedTotalAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        }


    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
