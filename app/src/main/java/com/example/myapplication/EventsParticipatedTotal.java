package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsParticipatedTotal extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EventsParticipatedTotalAdapter mAdapter;
    FirebaseUser user_id;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_participated_total);

        mRecyclerView=findViewById(R.id.events_participated_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        user_id = FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        String uid_user = user_id.getUid();
        ref = FirebaseDatabase.getInstance().getReference("My Events Participated").child(uid_user);

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
