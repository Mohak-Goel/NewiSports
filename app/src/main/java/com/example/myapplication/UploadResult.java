package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UploadResult extends AppCompatActivity {

    EditText WinnerUniversity, FirstRunnerUp, SecondRunnerUp;
    Button uploadResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_result);

        extractView();

        uploadResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    private void extractView(){
        WinnerUniversity = (EditText) findViewById(R.id.winner_university_name);
        FirstRunnerUp = (EditText) findViewById(R.id.first_runner_up_university_name);
        SecondRunnerUp = (EditText) findViewById(R.id.second_runner_up_university_name);
        uploadResult = (Button) findViewById(R.id.upload_result_button);
    }

}
