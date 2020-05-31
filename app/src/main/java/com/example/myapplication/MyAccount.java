package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {
    TextView un,ph,altph,utype,eid;
    DatabaseReference reff;
    FirebaseUser user_a;
    String uid_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    //    n=findViewById(R.id.namedisp);
        un=findViewById(R.id.univdisp);
    //    uroll=findViewById(R.id.rolldisp);
    //    dob=findViewById(R.id.dobdisp);
        ph=findViewById(R.id.phondisp);
        altph=findViewById(R.id.altphodisp);
        utype=findViewById(R.id.userdisp);
        eid=findViewById(R.id.emaildisp);
        user_a= FirebaseAuth.getInstance().getCurrentUser();
        uid_a=user_a.getUid();
        reff= FirebaseDatabase.getInstance().getReference().child("Registration Details").child(uid_a);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String university_na=dataSnapshot.child("UniversityName").getValue().toString();
                String phone_no=dataSnapshot.child("PhoneNumber").getValue().toString();
                String Alt_phone_no=dataSnapshot.child("AlternativeNumber").getValue().toString();
                String email_address=dataSnapshot.child("EmailAddress").getValue().toString();
                String user_type=dataSnapshot.child("UserType").getValue().toString();
                un.setText(university_na);
                ph.setText(phone_no);
                altph.setText(Alt_phone_no);
                eid.setText(email_address);
                utype.setText(user_type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void change_password(View v)
    {
        Intent I=new Intent(MyAccount.this,Forgotpass.class);
        startActivity(I);
    }
}
