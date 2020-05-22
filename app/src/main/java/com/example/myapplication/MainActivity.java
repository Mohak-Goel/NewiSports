package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLogin;
    TextView mRegister;
    TextView Forgot_Password;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    private static final String TAG = "AndroidClarified";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            }
        };
        setContentView(R.layout.activity_main);
        mEmail =findViewById(R.id.email);
        mPassword =findViewById(R.id.pass);
        mLogin=findViewById(R.id.login);
        mRegister =findViewById(R.id.register);
        Forgot_Password=findViewById(R.id.forgot);
        firebaseAuth=FirebaseAuth.getInstance();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = mEmail.getText().toString();
                String Password = mPassword.getText().toString();
                if (Email.isEmpty()) {
                    mEmail.setError("Please enter email id");
                    mEmail.requestFocus();
                } else if (Password.isEmpty()) {
                    mPassword.setError("Please enter password");
                    mPassword.requestFocus();
                } else if (Password.length() < 6) {
                    mPassword.setError("Password length minimum 6");
                    mPassword.requestFocus();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Select.class));
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Sigh in error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        Forgot_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(MainActivity.this,//Forgot.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }




}
