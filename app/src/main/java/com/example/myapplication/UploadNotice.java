package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadNotice extends AppCompatActivity {
    EditText E1, E2;
    DatabaseReference ref;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);
        E1 = findViewById(R.id.e1);
        E2 = findViewById(R.id.e2);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

    }

    public void funUN(View v) {
        String st1 = E1.getText().toString().trim();
        String st2 = E2.getText().toString().trim();
        if (st1.length() > 0 && st2.length() > 0) {
            ref = FirebaseDatabase.getInstance().getReference().child("Notice").child(uid);
            HashMap<String, String> user_details = new HashMap<>();
            user_details.put("UniversityName", st1);
            user_details.put("Notice", st2);
            ref.setValue(user_details);

        }
        else
        {
            Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(UploadNotice.this, GetNotice.class));
    }
}
