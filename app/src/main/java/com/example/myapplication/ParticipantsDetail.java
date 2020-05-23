package com.example.myapplication;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ParticipantsDetail extends AppCompatActivity {

      Button addParticipants;
      Button removeParticipants;
      LinearLayout pList;
      int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_detail);

         addParticipants = (Button)findViewById(R.id.addParticipantsButton);
         removeParticipants = (Button)findViewById(R.id.removeParticipantsButton);
         pList = (LinearLayout)findViewById(R.id.participantList);


         addParticipants.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  count++;
                  addParticipantsView();
            }
         });

         removeParticipants.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              if (count>1)
                  count--;
            }
        });
    }

    public void addParticipantsView(){
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(0,8,0,8);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(layoutParams);
        et.setHint("Enter Participant Name");
        et.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        et.setBackgroundResource(R.drawable.editbackgound);
        ll.addView(et);
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setPadding(0,4,0,4);
        Spinner sp1 = new Spinner(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sp1.setLayoutParams(layoutParams2);
        sp1.setScrollContainer(true);
        String[] testArray = getResources().getStringArray(R.array.blood);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, testArray );
        sp1.setAdapter(spinnerArrayAdapter);
        ll1.addView(sp1);
        Spinner sp2 = new Spinner(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams3.weight=1;
        sp2.setLayoutParams(layoutParams3);
        sp2.setScrollContainer(true);
        String[] testArray2 = getResources().getStringArray(R.array.sportsGame);
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, testArray2 );
        sp2.setAdapter(spinnerArrayAdapter2);
        ll1.addView(sp2);
        ll.addView(ll1);
        pList.addView(ll);
    }
}
