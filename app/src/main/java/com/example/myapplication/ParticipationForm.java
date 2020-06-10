package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticipationForm extends AppCompatActivity {

    EditText participantUnivCoachName,
            participantUnivCoachPhNo, participantUnivCoachEmail;

    RadioGroup radioGroupTransport, radioGroupFood, radioGroupLodging;

    RadioButton radioButtonTransport, radioButtonFood, radioButtonLodging;

    Button buttonNext;
    Button buttonReset;
    Toast t;

    CreateEvent createEvent;

    private long backPressedTime;

    FirebaseUser user_id;
    DatabaseReference firebaseDatabase;


    public static final String PARTICIPANT_UNIVERSITY_DETAIL = "com.example.myapplication.ParticipationForm";
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_form);

        createEvent = (CreateEvent)getIntent().getSerializableExtra("Home Event Detail");

        user_id= FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        String uid = user_id.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Registration Details").child(uid);

        extractView();

        final String id = (String)getIntent().getStringExtra("Event Detail Key").trim();

        t = Toast.makeText(getApplicationContext(), "Kindly Fill Participation Form", Toast.LENGTH_SHORT);
        t.show();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String
                        UnivCoachName = participantUnivCoachName.getText().toString(),
                        UnivCoachPhNo = participantUnivCoachPhNo.getText().toString(),
                        UnivCoachEmail = participantUnivCoachEmail.getText().toString();

                if (UnivCoachName.isEmpty() && UnivCoachPhNo.isEmpty() && UnivCoachEmail.isEmpty()){

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Fields are already empty !!", Toast.LENGTH_SHORT);
                    t.show();

                }

                else if (flag==0){
                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Press Reset button once again\nto Reset the fields of the form !!", Toast.LENGTH_LONG);
                    t.show();
                    flag=1;
                }

                else {
                    participantUnivCoachName.getText().clear();
                    participantUnivCoachPhNo.getText().clear();
                    participantUnivCoachEmail.getText().clear();
                    radioGroupTransport.check(R.id.transport_radio_yes);
                    radioGroupLodging.check(R.id.lodging_radio_yes);
                    radioGroupFood.check(R.id.food_radio_yes);

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), "Reset Successful !!", Toast.LENGTH_SHORT);
                    t.show();

                    flag=0;
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  UnivCoachName = participantUnivCoachName.getText().toString(),
                        UnivCoachPhNo = participantUnivCoachPhNo.getText().toString(),
                        UnivCoachEmail = participantUnivCoachEmail.getText().toString();

                int selectedId = radioGroupFood.getCheckedRadioButtonId();
                radioButtonFood = (RadioButton)findViewById(selectedId);

                boolean pfood = true;
                if (radioButtonFood.getId()==R.id.food_radio_no)    pfood = false;

                selectedId = radioGroupLodging.getCheckedRadioButtonId();
                radioButtonLodging = (RadioButton)findViewById(selectedId);
                boolean pLodging = true;
                if (radioButtonLodging.getId()==R.id.lodging_radio_no)  pLodging = false;

                selectedId = radioGroupTransport.getCheckedRadioButtonId();
                radioButtonTransport = (RadioButton)findViewById(selectedId);
                boolean pTransport = true;
                if (radioButtonTransport.getId()==R.id.transport_radio_no) pTransport = false;

                if (UnivCoachName.isEmpty() || UnivCoachPhNo.isEmpty() || UnivCoachEmail.isEmpty() ){

                    String error = "";

                    if (UnivCoachName.isEmpty())
                        error+= "Enter Coach Name\n";

                    if (UnivCoachPhNo.isEmpty())
                        error+= "Enter Coach Phone No.\n";

                    if (UnivCoachEmail.isEmpty())
                        error+= "Enter Coach Email\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();
                }

                else if (!isValidMobile(UnivCoachPhNo))
                {
                    String error = "";

                    error+="Invalid Coach Phone No.\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();

                }

                else if (!isEmailValid(UnivCoachEmail))
                {
                    String error = "";

                    error+="Invalid Coach E-mail\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();

                }

                else {


                        final boolean transport=pTransport, food=pfood, lodging=pLodging;
                        firebaseDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try {

                                    participantFormItem participantFormItemObj =new participantFormItem(Objects.requireNonNull(dataSnapshot.child("UniversityName").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("LocalAddress").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("City").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("State").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("PinCode").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("PhoneNumber").getValue()).toString().trim()
                                    , Objects.requireNonNull(dataSnapshot.child("EmailAddress").getValue()).toString().trim()
                                    , UnivCoachName, UnivCoachPhNo, UnivCoachEmail, transport, food, lodging, id);

                                    Intent i1 = new Intent(ParticipationForm.this, ParticipantsDetail.class);
                                    i1.putExtra(PARTICIPANT_UNIVERSITY_DETAIL, participantFormItemObj);
                                    i1.putExtra("Event Participated Detail", createEvent);
                                    startActivity(i1);
                                    finish();

                                }catch (Exception e){
                                    t.cancel();
                                    t=Toast.makeText(ParticipationForm.this, "Something went wrong", Toast.LENGTH_LONG);
                                    t.show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });




                }
            }
        });

    }

    private void extractView(){

        participantUnivCoachName = findViewById(R.id.participant_coach_name);
        participantUnivCoachEmail = findViewById(R.id.participant_coach_email);
        participantUnivCoachPhNo = findViewById(R.id.participant_coach_phone_no);
        radioGroupFood = findViewById(R.id.food_radio_group);
        radioGroupLodging = findViewById(R.id.lodging_radio_group);
        radioGroupTransport = findViewById(R.id.transport_radio_group);
        buttonNext = findViewById(R.id.participant_next_button);
        buttonReset = findViewById(R.id.reset_button);


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

    public static boolean isPostalCodeValid(String pcode) {
        boolean isValid = false;

        String expression = "^[1-9][0-9]{5}$";
        CharSequence inputStr = pcode;

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
