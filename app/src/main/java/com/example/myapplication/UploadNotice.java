package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadNotice extends AppCompatActivity {

    EditText uploadnoticetext;
    Button unotice_button;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        reference = FirebaseDatabase.getInstance().getReference("Notice Details");

        uploadnoticetext = (EditText)findViewById(R.id.uploadnoticetext);
        unotice_button = (Button)findViewById(R.id.unotice_button);

        unotice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotice();
            }
        });

    }

    private void addNotice(){
        String text = uploadnoticetext.getText().toString().trim();

        if(!TextUtils.isEmpty(text)){
            String id = reference.push().getKey();

            Unotice notice = new Unotice(id, text);

            reference.child(id).setValue(notice);
            Toast.makeText(this,"Uploaded Notice Successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Please enter a notice",Toast.LENGTH_SHORT).show();
        }
    }
}

