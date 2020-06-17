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

public class MyEventsCreatedTotal extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EventCreatedTotalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_created_total);

        mRecyclerView=findViewById(R.id.my_event_created_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        String user_type = (String)getIntent().getStringExtra("User Type").trim();
        FirebaseUser user_id= FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        String uid_user=user_id.getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("My Events Created").child(uid_user);

        FirebaseRecyclerOptions<CreateEvent> options = new FirebaseRecyclerOptions.Builder<CreateEvent>().setQuery(ref, CreateEvent.class).build();

        mAdapter = new EventCreatedTotalAdapter(MyEventsCreatedTotal.this, user_type,options);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new EventCreatedTotalAdapter.OnItemClickListener() {
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
