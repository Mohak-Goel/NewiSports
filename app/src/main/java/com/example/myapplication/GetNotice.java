package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetNotice extends AppCompatActivity {

    private FirebaseRecyclerOptions<model> options;
    private FirebaseRecyclerAdapter<model,MyviewHolder> adapter;
    private RecyclerView recyclerView;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_notice);
        ref= FirebaseDatabase.getInstance().getReference().child("Notice");
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options=new FirebaseRecyclerOptions.Builder<model>().setQuery(ref,model.class).build();
        adapter=new FirebaseRecyclerAdapter<model, MyviewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull model model) {
            holder.textViewUniversityName.setText(model.getUniversityName());
            holder.textViewNotice.setText(model.getNotice());
            }

            @NonNull
            @Override
            public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent,false);

                return new MyviewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
