package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetResult extends AppCompatActivity {

    private TextView participant1, participant2, participant3;

    DatabaseReference resultDatabase;
    String EDkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);

        participant1 = findViewById(R.id.winner);
        participant2 = findViewById(R.id.first_runner_up);
        participant3 = findViewById(R.id.sec_runner_up);

        Button backButton = findViewById(R.id.back_button);

        EDkey = (String)getIntent().getStringExtra("ED Key3").trim();
        resultDatabase = FirebaseDatabase.getInstance().getReference("Result Details");

        resultDatabase.orderByKey().equalTo(EDkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot: dataSnapshot.getChildren()){
                    ResultDetail resultDetail = suggestionSnapshot.getValue(ResultDetail.class);
                    assert resultDetail != null;
                    participant1.setText(resultDetail.getP1());
                    participant2.setText(resultDetail.getP2());
                    participant3.setText(resultDetail.getP3());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
