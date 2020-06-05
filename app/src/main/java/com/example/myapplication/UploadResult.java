package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class UploadResult extends AppCompatActivity {

    RecyclerView resultList;
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
        uploadResult = (Button) findViewById(R.id.upload_result_button);
        resultList = (RecyclerView) findViewById(R.id.result_list);
    }

}
