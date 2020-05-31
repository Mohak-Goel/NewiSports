package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {
    TextView un,Laddr,cityad,statead,pincodead,ph,altph,utype,eid;
    DatabaseReference reff;
    FirebaseUser user_a;
    String uid_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        un=findViewById(R.id.univdisp);
        Laddr=findViewById(R.id.Laddrdisp);
        cityad=findViewById(R.id.citydisp);
        statead=findViewById(R.id.statedisp);
        pincodead=findViewById(R.id.pincodedisp);
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
                try {
                    String university_na = dataSnapshot.child("UniversityName").getValue().toString();
                    String laddr = dataSnapshot.child("LocalAddress").getValue().toString();
                    String cty = dataSnapshot.child("City").getValue().toString();
                    String stt = dataSnapshot.child("State").getValue().toString();
                    String pinc = dataSnapshot.child("PinCode").getValue().toString();
                    String phone_no = dataSnapshot.child("PhoneNumber").getValue().toString();
                    String Alt_phone_no = dataSnapshot.child("AlternativeNumber").getValue().toString();
                    String email_address = dataSnapshot.child("EmailAddress").getValue().toString();
                    String user_type = dataSnapshot.child("UserType").getValue().toString();
                    un.setText(university_na);
                    Laddr.setText(laddr);
                    cityad.setText(cty);
                    statead.setText(stt);
                    pincodead.setText(pinc);
                    ph.setText(phone_no);
                    altph.setText(Alt_phone_no);
                    utype.setText(user_type);
                    eid.setText(email_address);
                }
                catch(Exception e)
                {
                    Toast.makeText(MyAccount.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

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
