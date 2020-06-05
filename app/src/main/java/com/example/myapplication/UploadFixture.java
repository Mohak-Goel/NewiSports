package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UploadFixture extends AppCompatActivity {

    private EditText fixtureDate, fixtureTime;
    private AutoCompleteTextView participant1, participant2;
    private Button buttonAddFixture, uploadFixture;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private RecyclerView fixtureList;
    private FixtureAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<FixtureDetail> fixtureDetails;

    private DatabaseReference participantDatabase;
    private DatabaseReference fixtureDatabase;
    private long mLastClickTime= 0;
    List<String> suggestion;
    int flag1=1, flag2 = 0;

    Toast toast;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_fixture);

        toast = Toast.makeText(getApplicationContext(), "Kindly Enter Fixture Details !!", Toast.LENGTH_SHORT);
        toast.show();
        suggestion = new ArrayList<>();
        fixtureDetails = new ArrayList<>();
        fixtureList = (RecyclerView)findViewById(R.id.fixture_list);
        fixtureList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FixtureAdapter(fixtureDetails);
        fixtureList.setLayoutManager(mLayoutManager);
        fixtureList.setAdapter(mAdapter);

        extractView();



        fixtureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadFixture.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        fixtureDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        fixtureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int min = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(UploadFixture.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String amPm = "";
                        if (selectedHour > 12 && selectedHour!=12) {
                            selectedHour-=12;
                            amPm = "PM";
                        } else {
                            if (selectedHour==12)
                                amPm = "PM";
                            else if (selectedHour==0){
                                selectedHour = 12;
                                amPm = "AM";
                            }
                            else
                                amPm = "AM";

                        }

                        fixtureTime.setText( String.format("%02d:%02d", selectedHour, selectedMinute) + " "+amPm);
                    }
                }, hour, min, false);
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();

            }
        });

        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        participantDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot: dataSnapshot.getChildren()){
                    ParticipantUniversity participant = suggestionSnapshot.getValue(ParticipantUniversity.class);
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


        buttonAddFixture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String p1 = participant1.getText().toString();
                String p2 = participant2.getText().toString();
                String date = fixtureDate.getText().toString();
                String time = fixtureTime.getText().toString();

                if (p1.equalsIgnoreCase(p2) && !p1.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Both the opponents are same\n" +
                            "Try Again :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (p1.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "First Participant University Name Field Empty\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (p2.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Second Participant University Name Field Empty!!\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (date.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Fixture Date Empty\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (time.isEmpty())
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Fixture Time Empty\n" +
                            "Kindly Fill it !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(!isParticipantValid(p1)){
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Incorrect first participant name\n" +
                            "Select correct name !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(!isParticipantValid(p2)){
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Incorrect second participant name\n" +
                            "Select correct name !! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (fixtureDetails.contains(new FixtureDetail(p1,p2,date,time)))
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Fixture Already Exist!! :-(", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    try {
                        if (flag1==1)
                        {
                            fixtureDetails.remove(0);
                            mAdapter.notifyItemRemoved(fixtureDetails.size()-1);
                            flag1 = 0;
                        }
                        fixtureDetails.add(new FixtureDetail(p1, p2, date, time));
                        mAdapter.notifyItemInserted(fixtureDetails.size() - 1);
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "Fixture Added Successfully!!", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (NumberFormatException e) {
                    }
                }
            }
        });

        mAdapter.setOnClickListener(new FixtureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                if (flag1==1)
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "Kindly Add Fixture!!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                    removeItem(position);
            }
        });

        if (fixtureDetails.size()==0){
            flag1 = 1;
            fixtureDetails.add(new FixtureDetail("Kindly Add Fixture", "No Fixture added !!", "NA", "NA"));
            mAdapter.notifyItemInserted(fixtureDetails.size()-1);
            toast.cancel();
            toast = Toast.makeText(getApplicationContext(), "Kindly Add Fixture !!", Toast.LENGTH_SHORT);
            toast.show();
        }

        uploadFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag1==1)
                {
                    toast.cancel();
                    toast = Toast.makeText(getApplicationContext(), "No Fixture Added. Kindly add fixture", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    if (SystemClock.elapsedRealtime() - mLastClickTime > 5000) {
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "Tap this button once again if you are sure that you have filled the details correctly!! !!", Toast.LENGTH_LONG);
                        toast.show();
                    } else {

                        //FixtureDetail fixtureDetail = new FixtureDetail(participant1.getText().toString(), participant2.getText().toString(), fixtureDate.getText().toString(), fixtureTime.getText().toString());
                        String key = fixtureDatabase.push().getKey();
                        assert key != null;
                        fixtureDatabase.setValue(fixtureDetails);
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "Fixture Uploaded Successfully !! :-)", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                }
            }
        });

    }



    public void removeItem(int position) {


            fixtureDetails.remove(position);
            mAdapter.notifyItemRemoved(position);

            toast.cancel();
            toast = Toast.makeText(getApplicationContext(), "Fixture Removed Successfully !!", Toast.LENGTH_SHORT);
            toast.show();

            if (fixtureDetails.size()==0){
                flag1 = 1;
                fixtureDetails.add(new FixtureDetail("Kindly Add Fixture", "No Fixture added !!", "NA", "NA"));
                mAdapter.notifyItemInserted(fixtureDetails.size()-1);
                toast.cancel();
                toast = Toast.makeText(getApplicationContext(), "Kindly Add Fixture !!", Toast.LENGTH_SHORT);
                toast.show();
            }

    }

    private void extractView() {

        fixtureDate = (EditText) findViewById(R.id.fixturedate);
        fixtureTime = (EditText) findViewById(R.id.fixturetime);
        participant1 = (AutoCompleteTextView) findViewById(R.id.participant_university_1);
        participant2 = (AutoCompleteTextView) findViewById(R.id.participant_university_2);
        buttonAddFixture = (Button)findViewById(R.id.addFixtureButton);
        uploadFixture = (Button)findViewById(R.id.submit_button_fixture);
        participantDatabase = FirebaseDatabase.getInstance().getReference("Participant Details");
        fixtureDatabase = FirebaseDatabase.getInstance().getReference("Fixture Details");
    }

    private boolean isParticipantValid(String participant){

        if (suggestion.contains(participant))
            return true;

        return false;
    }

}
