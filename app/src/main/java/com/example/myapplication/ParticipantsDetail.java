package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParticipantsDetail extends AppCompatActivity {

    private RecyclerView participantList;
    private ParticipantsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ParticipantItem> aboutParticipant;
    Button addParticipantButton;
    EditText pName, pEmail, pPhNo;
    Spinner spinnerBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_detail);

        aboutParticipant = new ArrayList<>();
        addParticipantButton = (Button)findViewById(R.id.addParticipantsButton);
        pName = (EditText)findViewById(R.id.participant_name);
        pEmail = (EditText)findViewById(R.id.participant_email);
        pPhNo = (EditText)findViewById(R.id.participant_phoneNo);
        spinnerBG = (Spinner)findViewById(R.id.bloodGroupSpinner_1);

        participantList = findViewById(R.id.participantList);
        participantList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ParticipantsAdapter(aboutParticipant);

        participantList.setLayoutManager(mLayoutManager);
        participantList.setAdapter(mAdapter);

        if (aboutParticipant.size()==0)
            Toast.makeText(getApplicationContext(),"Kindly Add Participants!!", Toast.LENGTH_LONG).show();

        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = pName.getText().toString();
                String phNo = pPhNo.getText().toString();
                String email = pEmail.getText().toString();
                String bg = spinnerBG.getSelectedItem().toString();

                if (name.isEmpty() || phNo.isEmpty() || email.isEmpty() || spinnerBG.getSelectedItemPosition()==0){

                    String error = "";
                    if (name.isEmpty())
                        error = "Enter Participant Name\n";

                    if (email.isEmpty())
                        error+= "Enter Participant Email\n";

                    if (phNo.isEmpty())
                        error+="Enter Participant Phone No.\n";

                    if (spinnerBG.getSelectedItemPosition()==0)
                        error+= "Select Blood Group";

                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }

                else {
                    try {
                        pName.setText("");
                        pEmail.setText("");
                        pPhNo.setText("");
                        spinnerBG.setSelection(0);
                        aboutParticipant.add(new ParticipantItem(name, email , phNo, bg));
                        mAdapter.notifyItemInserted(aboutParticipant.size() - 1);
                        Toast.makeText(getApplicationContext(), "Participant Added Successfully!!", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {

                    }
                }
            }
        });


        mAdapter.setOnClickListener(new ParticipantsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
            removeItem(position);
            if (aboutParticipant.size()==0)
                Toast.makeText(getApplicationContext(),"Kindly Add Participants!!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void removeItem(int position){
        aboutParticipant.remove(position);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getApplicationContext(), "Participant Removed Successfully!!", Toast.LENGTH_SHORT).show();
    }

}
