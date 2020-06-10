package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadResult extends AppCompatActivity {

    private AutoCompleteTextView participant1, participant2, participant3;
    private Button uploadResult;


    DatabaseReference participantDatabase;
    DatabaseReference resultDatabase;
    List<String> suggestion;
    String EDkey;

    Toast toast;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_result);

        suggestion = new ArrayList<>();

        participant1 = findViewById(R.id.winner_university);
        participant2 = findViewById(R.id.first_runner_up_university);
        participant3 = findViewById(R.id.sec_runner_up_university);
        uploadResult = findViewById(R.id.upload_result);
        participantDatabase = FirebaseDatabase.getInstance().getReference("Participant Details");
        EDkey = (String)getIntent().getStringExtra("ED Key2").trim();
        resultDatabase = FirebaseDatabase.getInstance().getReference("Result Details");

        toast = Toast.makeText(getApplicationContext(), "Kindly Upload Result", Toast.LENGTH_SHORT);
        toast.show();

        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        participantDatabase.orderByChild("eventid").equalTo(EDkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot: dataSnapshot.getChildren()){
                    ParticipantUniversity participant = suggestionSnapshot.getValue(ParticipantUniversity.class);
                    assert participant != null;
                    String suggest = participant.getParticipantUnivName()+", "+participant.getParticipantUnivCity()+", "+participant.getParticipantUnivPostalCode();
                    suggestion.add(suggest);
                    autoComplete.add(suggest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        participant1.setAdapter(autoComplete);
        participant2.setAdapter(autoComplete);
        participant3.setAdapter(autoComplete);

        participant1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                participant1.showDropDown();
                participant1.requestFocus();
                return false;
            }
        });

        participant1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    participant1.showDropDown();
                    participant1.requestFocus();
                }
            }
        });

        participant3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                participant3.showDropDown();
                participant3.requestFocus();
                return false;
            }
        });

        participant3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    participant3.showDropDown();
                    participant3.requestFocus();
                }
            }
        });

        participant2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                participant2.showDropDown();
                participant2.requestFocus();
                return false;
            }
        });

        participant2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    participant2.showDropDown();
                    participant2.requestFocus();
                }
            }
        });


        uploadResult.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String p1 = participant1.getText().toString();
                String p2 = participant2.getText().toString();
                String p3 = participant3.getText().toString();

                if(p1.equalsIgnoreCase(p2) && p1.equalsIgnoreCase(p3) && !p1.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Winner, 1st & 2nd Runner-Up is same\n" +
                            "Try Again :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (p1.equalsIgnoreCase(p2) && !p1.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Winner & 1st Runner-up is same\n" +
                            "Try Again :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(p2.equalsIgnoreCase(p3) && !p2.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "1st & 2nd Runner-up is same\n" +
                            "Try Again :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(p1.equalsIgnoreCase(p3) && !p3.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Winner & 2nd Runner-up is same\n" +
                            "Try Again :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (p1.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Winner University Field Empty\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (p2.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "1st Runner-up Field Empty!!\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }


                else if (p3.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "2nd Runner-up Field Empty!!\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(isParticipantValid(p1)){
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Incorrect Winner name\n" +
                            "Select correct name !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(isParticipantValid(p2)){
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Incorrect 1st Runner-up name\n" +
                            "Select correct name !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }


                else if(isParticipantValid(p3)){
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Incorrect 2nd Runner-up name\n" +
                            "Select correct name !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    try {
                        Toast.makeText(getApplicationContext(), "Result Added Successfully!!", Toast.LENGTH_SHORT).show();
                        resultDatabase.child(EDkey).setValue(new ResultDetail(p1, p2, p3));
                    } catch (NumberFormatException ignored) {
                    }
                    finish();
                }
            }
        });


    }



    private boolean isParticipantValid(String participant){

        return !suggestion.contains(participant);
    }

}
