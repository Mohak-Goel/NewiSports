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
    EditText pName;
    Spinner spinnerBG, spinnerSN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_detail);

        aboutParticipant = new ArrayList<>();
        addParticipantButton = (Button)findViewById(R.id.addParticipantsButton);
        pName = (EditText)findViewById(R.id.participant_name);
        spinnerBG = (Spinner)findViewById(R.id.bloodGroupSpinner_1);
        spinnerSN = (Spinner)findViewById(R.id.sportsName_1);

        participantList = findViewById(R.id.participantList);
        participantList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ParticipantsAdapter(aboutParticipant);

        participantList.setLayoutManager(mLayoutManager);
        participantList.setAdapter(mAdapter);

        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = pName.getText().toString();
                String bg = spinnerBG.getSelectedItem().toString();
                String sn = spinnerSN.getSelectedItem().toString();

                if (name.isEmpty() || spinnerSN.getSelectedItemPosition()==0 || spinnerBG.getSelectedItemPosition()==0){

                    String error = "";
                    if (name.isEmpty())
                        error = "Enter Participant Name\n";

                    if (spinnerBG.getSelectedItemPosition()==0)
                        error+= "Select Blood Group\n";

                    if (spinnerSN.getSelectedItemPosition()==0)
                        error+="Select Sport Name";

                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                }

                else {
                    try {
                        pName.setText("");
                        spinnerBG.setSelection(0);
                        spinnerSN.setSelection(0);
                        aboutParticipant.add(new ParticipantItem(name, bg, sn));
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
            }
        });

    }

    public void removeItem(int position){
        aboutParticipant.remove(position);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getApplicationContext(), "Participant Removed Successfully!!", Toast.LENGTH_SHORT).show();
    }

}
