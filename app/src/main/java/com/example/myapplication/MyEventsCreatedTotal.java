package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyEventsCreatedTotal extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EventCreatedTotalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_created_total);

        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        FirebaseUser user_id= FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        String uid_user=user_id.getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("My Events Created").child(uid_user);

        FirebaseRecyclerOptions<CreateEvent> options = new FirebaseRecyclerOptions.Builder<CreateEvent>().setQuery(ref, CreateEvent.class).build();

        mAdapter = new EventCreatedTotalAdapter(MyEventsCreatedTotal.this, options);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new EventCreatedTotalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
}
