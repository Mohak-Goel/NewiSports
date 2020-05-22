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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Register extends AppCompatActivity {

    Button Register;
    EditText Email,Password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
}
