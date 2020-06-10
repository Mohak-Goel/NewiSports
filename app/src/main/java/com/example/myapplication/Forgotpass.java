package com.example.myapplication;

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
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    EditText userEmail;
    Button userPass;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        Toast.makeText(Forgotpass.this, "Please Wait...", Toast.LENGTH_SHORT).show();
        userEmail=findViewById(R.id.etUserEmail);
        userPass=findViewById(R.id.btnforgotpass);
        firebaseAuth= FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Forgotpass.this,"Password sent to your Email",Toast.LENGTH_LONG).show();
                            Intent I=new Intent(Forgotpass.this,MainActivity.class);
                            startActivity(I);
                        }
                        else
                        {
                            Toast.makeText(Forgotpass.this,
                                    task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
