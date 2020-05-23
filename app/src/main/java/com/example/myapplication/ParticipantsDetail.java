package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ParticipantsDetail extends AppCompatActivity {

    //  ScrollView sv;
      LinearLayout participantList;
      Button addParticipants;
      Button removeParticipants;
   // TextView noParticipantText;
      int count = 0;
      String idString="participant_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_detail);

         addParticipants = (Button)findViewById(R.id.addParticipantsButton);
         removeParticipants = (Button)findViewById(R.id.removeParticipantsButton);
         participantList = (LinearLayout)findViewById(R.id.participant_1);

       // Spinner bloodGroup = (Spinner)findViewById(R.id.bloodGroupSpinner);


         addParticipants.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  count++;
                //  addParticipantsView();
            }
         });

         removeParticipants.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              if (count>0)
                  count--;
            }
        });
    }

   /* private void addParticipantsView(){
    }*/
}
