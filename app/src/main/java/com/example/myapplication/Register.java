package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity {

    EditText E1,E2,E3,E4,E5,E6,E7,E8;
    Button Register;
    EditText Email,Password;
    FirebaseAuth firebaseAuth;
    private DatabaseReference dbref;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        E1=findViewById(R.id.Laddress);
        E2=findViewById(R.id.uname);
        E3=findViewById(R.id.city);
        E4=findViewById(R.id.state);
        E5=findViewById(R.id.pno);
        E6=findViewById(R.id.pno2);
        E7=findViewById(R.id.usertype);
        E8=findViewById(R.id.pincode);
        Register=findViewById(R.id.Rregister);
        Email=findViewById(R.id.Remail);
        Password=findViewById(R.id.Rpass);
        firebaseAuth=FirebaseAuth.getInstance();




        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= Email.getText().toString();
                String password= Password.getText().toString();
                if(email.isEmpty()){
                    Email.setError("Please enter email id");
                    Email.requestFocus();
                }
                else if(password.isEmpty()){
                    Password.setError("Please enter password");
                    Password.requestFocus();
                }
                else if(password.length()<6){
                    Password.setError("Password length minimum 6");
                    Password.requestFocus();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                               // String str=get
                                user=FirebaseAuth.getInstance().getCurrentUser();
                                uid=user.getUid();
                                submit_to_database(uid);
                                Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Register.this, MainActivity.class));
                            } else {
                                Toast.makeText(Register.this, "Sigh up error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }

    public void submit_to_database(String uid)
    {
        try
        {
            String record_email= Email.getText().toString();

            String st1 = E1.getText().toString().trim();
            String st2 = E2.getText().toString().trim();
            String st3 = E3.getText().toString().trim();
            String st4 = E4.getText().toString().trim();
            String st5 = E5.getText().toString().trim();
            String st6 = E6.getText().toString().trim();
            String st7 = E7.getText().toString().trim();
            String st8 = E7.getText().toString().trim();
            if (st7.length() > 0 &&st6.length() > 0 && st5.length() > 0 && st2.length() > 0 ) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Registration Details").child(uid);
                HashMap<String, String> user_details = new HashMap<>();
                user_details.put("LocalAddress", st1);
                user_details.put("UniversityName", st2);
                user_details.put("City", st3);
                user_details.put("State", st4);
                user_details.put("PhoneNumber", st5);
                user_details.put("AlternativeNumber", st6);
                user_details.put("UserType", st7);
                user_details.put("PinCode", st8);
                user_details.put("EmailAddress",record_email);
                dbref.setValue(user_details);

            } else {
                Toast.makeText(this, "Please Enter All your details", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Registration Error",e.toString());
            Toast.makeText(this,"Something went wrong" , Toast.LENGTH_LONG).show();

        }
    }
}
