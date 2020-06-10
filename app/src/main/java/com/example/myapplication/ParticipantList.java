package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ParticipantList extends AppCompatActivity {
    private FirebaseRecyclerOptions<model2> options2;
    private FirebaseRecyclerAdapter<model2, MyviewHolder2> adapter;
    private RecyclerView recyclerVie;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);
        Toast.makeText(ParticipantList.this, "Please Wait...", Toast.LENGTH_LONG).show();
        ref= FirebaseDatabase.getInstance().getReference().child("Participant Details");
        recyclerVie=findViewById(R.id.recyclerV);
        recyclerVie.setHasFixedSize(true);
        recyclerVie.setLayoutManager(new LinearLayoutManager(this));

        options2=new FirebaseRecyclerOptions.Builder<model2>().setQuery(ref,model2.class).build();

        adapter= new FirebaseRecyclerAdapter<model2, MyviewHolder2>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull MyviewHolder2 holder, int position, @NonNull model2 model) {
              //  holder.universityname.setText(model.getParticipantUnivName());
              //  holder.universitycoachname.setText(model.getParticipantUnivCoachName());
                holder.universitynam.setText(model.getParticipantUnivName());
                holder.universitycname.setText(model.getParticipantUnivCoachName());
            }

            @NonNull
            @Override
            public MyviewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout2,parent,false);

                return new MyviewHolder2(v1);
            }
        };
        adapter.startListening();
        recyclerVie.setAdapter(adapter);

    }
}