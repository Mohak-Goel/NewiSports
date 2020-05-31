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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticipantsDetail extends AppCompatActivity {

    private RecyclerView participantList;
    private ParticipantsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ParticipantItem> aboutParticipant;

    ParticipantUniversity participantUnivDetail;
    participantFormItem participantFormItemClass;

    Button addParticipantButton, submitButton;

    EditText pName, pEmail, pPhNo;
    Spinner spinnerBG;

    int flag=0, flag2 = 0, count1=0, count2=0;

    Toast t;
    private long backPressedTime;

    DatabaseReference databaseParticipant;

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
        submitButton = (Button)findViewById(R.id.participant_detail_submit_button);
        databaseParticipant = FirebaseDatabase.getInstance().getReference("Participant Details");

        participantList = findViewById(R.id.participantList);
        participantList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ParticipantsAdapter(aboutParticipant);

        participantList.setLayoutManager(mLayoutManager);
        participantList.setAdapter(mAdapter);

        participantFormItemClass = (participantFormItem) getIntent().getSerializableExtra("com.example.myapplication.ParticipationForm");

        if (aboutParticipant.size()==0) {
            aboutParticipant.add(new ParticipantItem("NO PARTICIPANT ADDED", "Kindly Add Participant!!", "NA", "NA"));
            mAdapter.notifyItemInserted(aboutParticipant.size()-1);
            flag = 1;
            flag2=1;
            t = Toast.makeText(getApplicationContext(), "Kindly Add Participants!!", Toast.LENGTH_SHORT);
            t.show();
        }

        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = pName.getText().toString();
                String phNo = pPhNo.getText().toString();
                String email = pEmail.getText().toString();
                String bg = spinnerBG.getSelectedItem().toString();

                if (name.isEmpty() || phNo.isEmpty() || email.isEmpty() || spinnerBG.getSelectedItemPosition()==0){

                    flag2=1;
                    String error = "";
                    if (name.isEmpty())
                        error = "Enter Participant Name\n";

                    if (email.isEmpty())
                        error+= "Enter Participant Email\n";

                    if (phNo.isEmpty())
                        error+="Enter Participant Phone No.\n";

                    if (spinnerBG.getSelectedItemPosition()==0)
                        error+= "Select Blood Group";
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();
                }

                else if (!isValidMobile(phNo)){
                    flag2=1;
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Invalid Participant Phone No.!!", Toast.LENGTH_LONG);
                    t.show();
                }

                else if(!isEmailValid(email)){
                    flag2=1;
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Invalid Participant E-mail!!", Toast.LENGTH_LONG);
                    t.show();
                }

                else {
                    try {

                        if (flag==1){
                            flag = 0;
                            aboutParticipant.remove(0);
                            mAdapter.notifyItemRemoved(aboutParticipant.size()-1);
                        }
                        flag2 = 0;
                        pName.setText("");
                        pEmail.setText("");
                        pPhNo.setText("");
                        spinnerBG.setSelection(0);
                        aboutParticipant.add(new ParticipantItem(name, email , phNo, bg));
                        mAdapter.notifyItemInserted(aboutParticipant.size() - 1);
                        t.cancel();
                        t = Toast.makeText(getApplicationContext(), "Participant Added Successfully!!", Toast.LENGTH_SHORT);
                        t.show();
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

                if (flag == 0)
                    removeItem(position);

                if (aboutParticipant.size()==0) {
                    aboutParticipant.add(new ParticipantItem("NO PARTICIPANT ADDED", "Kindly Add Participant!!", "NA", "NA"));
                    mAdapter.notifyItemInserted(aboutParticipant.size() - 1);
                    flag = 1;
                    flag2=1;
                }

                if (flag==1){
                    flag2=1;
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Kindly Add Participants!!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aboutParticipant.size()==0||flag2==1) {
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Kindly Add Participants!!", Toast.LENGTH_SHORT);
                    t.show();
                }

                else {

                    if (count1==0) {
                        t.cancel();
                        t = Toast.makeText(getApplicationContext(), "Tap submit button once again if you are sure that \nyou have entered the details correctly !!", Toast.LENGTH_LONG);
                        t.show();
                        count1++;
                    }
                    else {
                        count1=0;

                        participantUnivDetail = new ParticipantUniversity(participantFormItemClass.getParticipantUnivName(),participantFormItemClass.getParticipantUnivAddress(),participantFormItemClass.getParticipantUnivCity(),participantFormItemClass.getParticipantUnivState(), participantFormItemClass.getParticipantUnivPostalCode(), participantFormItemClass.getParticipantUnivPhNo(), participantFormItemClass.getParticipantUnivEmail(), participantFormItemClass.getParticipantUnivCoachName(), participantFormItemClass.getParticipantUnivCoachPhNo(), participantFormItemClass.getParticipantUnivCoachEmail(), aboutParticipant, participantFormItemClass.isPtransport(), participantFormItemClass.isPfood(), participantFormItemClass.isPlodging());

                        databaseParticipant.child(participantUnivDetail.getParticipantUnivCoachPhNo()).setValue(participantUnivDetail);

                        t.cancel();
                        t = Toast.makeText(getApplicationContext(), "Congratulations!! You have successfully Participated in this event", Toast.LENGTH_LONG);
                        t.show();

                    }
                }
            }
        });

    }

    public void removeItem(int position){
        aboutParticipant.remove(position);
        mAdapter.notifyItemRemoved(position);
        t.cancel();
        t = Toast.makeText(getApplicationContext(), "Participant Removed Successfully!!", Toast.LENGTH_SHORT);
        t.show();
    }

    private boolean isValidMobile(String phone) {
        String expression = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            t.cancel();
            super.onBackPressed();
            return;
        } else {
            t.cancel();
            t = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            t.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
