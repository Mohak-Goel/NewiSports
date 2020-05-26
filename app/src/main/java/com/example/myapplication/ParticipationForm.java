package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticipationForm extends AppCompatActivity {

    EditText participantUnivName, participantUnivAddress, participantUnivCity, participantUnivPostalCode,
            participantUnivPhNo, participantUnivEmail, participantUnivCoachName,
            participantUnivCoachPhNo, participantUnivCoachEmail;
    Spinner participantUnivState;
    RadioGroup radioGroupTransport, radioGroupFood, radioGroupLodging;
    RadioButton radioButtonTransport, radioButtonFood, radioButtonLodging;

    Button buttonNext;
    Button buttonReset;
    Toast t;

    ArrayList<participantFormItem> participantFormItemArrayList;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_form);

        extractView();

        t = Toast.makeText(getApplicationContext(), "Kindly Fill Participation Form", Toast.LENGTH_SHORT);
        t.show();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UnivName = participantUnivName.getText().toString(),
                        UnivAddress = participantUnivAddress.getText().toString(),
                        UnivCity = participantUnivCity.getText().toString(),
                        UnivPostalCode = participantUnivPostalCode.getText().toString(),
                        UnivPhNo = participantUnivPhNo.getText().toString(),
                        UnivEmail = participantUnivEmail.getText().toString(),
                        UnivCoachName = participantUnivCoachName.getText().toString(),
                        UnivCoachPhNo = participantUnivCoachPhNo.getText().toString(),
                        UnivCoachEmail = participantUnivCoachEmail.getText().toString();

                if (
                        UnivName.isEmpty() && UnivAddress.isEmpty() && UnivCity.isEmpty() && participantUnivState.getSelectedItemPosition()==0 &&
                                UnivPostalCode.isEmpty() && UnivPhNo.isEmpty() && UnivEmail.isEmpty() && UnivCoachName.isEmpty() &&
                                UnivCoachPhNo.isEmpty() && UnivCoachEmail.isEmpty()
                ){

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
                    participantUnivName.getText().clear();
                    participantUnivAddress.getText().clear();
                    participantUnivCity.getText().clear();
                    participantUnivState.setSelection(0);
                    participantUnivPostalCode.getText().clear();
                    participantUnivPhNo.getText().clear();
                    participantUnivEmail.getText().clear();
                    participantUnivCoachName.getText().clear();
                    participantUnivPhNo.getText().clear();
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
                String UnivName = participantUnivName.getText().toString(),
                        UnivAddress = participantUnivAddress.getText().toString(),
                        UnivCity = participantUnivCity.getText().toString(),
                        UnivState = participantUnivState.getSelectedItem().toString(),
                        UnivPostalCode = participantUnivPostalCode.getText().toString(),
                        UnivPhNo = participantUnivPhNo.getText().toString(),
                        UnivEmail = participantUnivEmail.getText().toString(),
                        UnivCoachName = participantUnivCoachName.getText().toString(),
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

                if (
                        UnivName.isEmpty()|| UnivAddress.isEmpty() || UnivCity.isEmpty() || participantUnivState.getSelectedItemPosition()==0 ||
                        UnivPostalCode.isEmpty() || UnivPhNo.isEmpty() || UnivEmail.isEmpty() || UnivCoachName.isEmpty() ||
                        UnivCoachPhNo.isEmpty() || UnivCoachEmail.isEmpty()
                   ){

                    String error = "";

                    if (UnivName.isEmpty())
                        error+= "Enter University Name\n";

                    if (UnivAddress.isEmpty())
                        error+= "Enter University Address\n";

                    if (UnivCity.isEmpty())
                        error+= "Enter University City\n";

                    if (participantUnivState.getSelectedItemPosition()==0)
                        error+= "Select University State\n";

                    if (UnivAddress.isEmpty())
                        error+= "Enter University Address\n";

                    if (UnivPostalCode.isEmpty())
                        error+= "Enter University Postal Code\n";

                    if (UnivPhNo.isEmpty())
                        error+= "Enter University Phone No.\n";

                    if (UnivEmail.isEmpty())
                        error+= "Enter University Email\n";

                    if (UnivCoachName.isEmpty())
                        error+= "Enter Coach Name\n";

                    if (UnivCoachPhNo.isEmpty())
                        error+= "Enter Coach Phone No.\n";

                    if (UnivCoachEmail.isEmpty())
                        error+= "Enter University Email\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();
                }

                else if (!isValidMobile(UnivCoachPhNo) || !isValidMobile(UnivPhNo))
                {
                    String error = "";

                    if (!isValidMobile(UnivPhNo))
                        error+="Invalid University Phone No.\n";

                    if (!isValidMobile(UnivCoachPhNo))
                        error+="Invalid Coach Phone No.\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();

                }

                else if (!isEmailValid(UnivEmail) || !isEmailValid(UnivCoachEmail) || !isPostalCodeValid(UnivPostalCode))
                {
                    String error = "";

                    if (!isPostalCodeValid(UnivPostalCode))
                        error+="Invalid Postal Code\n";

                    if (!isEmailValid(UnivEmail))
                        error+="Invalid University E-mail\n";

                    if (!isEmailValid(UnivCoachEmail))
                        error+="Invalid Coach E-mail\n";

                    error+= ":-( :-( :-(";

                    t.cancel();
                    t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
                    t.show();

                }

                else {

                    participantFormItemArrayList.add(new participantFormItem(UnivName, UnivAddress, UnivCity, UnivState,
                            UnivPostalCode, UnivPhNo, UnivEmail, UnivCoachName, UnivCoachPhNo, UnivCoachEmail, pTransport, pfood, pLodging));
                    Intent i1 = new Intent(ParticipationForm.this, ParticipantsDetail.class);
                    startActivity(i1);
                }
            }
        });

    }

    private void extractView(){

        participantUnivName = findViewById(R.id.participant_university_name);
        participantUnivAddress = findViewById(R.id.participant_university_address);
        participantUnivCity = findViewById(R.id.participant_university_city);
        participantUnivState = findViewById(R.id.participant_university_state);
        participantUnivPostalCode = findViewById(R.id.participant_university_post_code);
        participantUnivPhNo = findViewById(R.id.participant_university_phone_number);
        participantUnivEmail = findViewById(R.id.participant_university_email);
        participantUnivCoachName = findViewById(R.id.participant_coach_name);
        participantUnivCoachEmail = findViewById(R.id.participant_coach_email);
        participantUnivCoachPhNo = findViewById(R.id.participant_coach_phone_no);
        radioGroupFood = findViewById(R.id.food_radio_group);
        radioGroupLodging = findViewById(R.id.lodging_radio_group);
        radioGroupTransport = findViewById(R.id.transport_radio_group);
        buttonNext = findViewById(R.id.participant_next_button);
        buttonReset = findViewById(R.id.reset_button);

        participantFormItemArrayList = new ArrayList<>();

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

}
