package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RaiseQuery extends AppCompatActivity {

    EditText rquery;
    Button raisequerybutton;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_query);

        reference = FirebaseDatabase.getInstance().getReference("Query Details");

        rquery = (EditText)findViewById(R.id.rquery);
        raisequerybutton=(Button)findViewById(R.id.raisequerybutton);

        raisequerybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              addQuery();
            }
        });
    }

    private void addQuery()
    {
        String text = rquery.getText().toString().trim();

        if(!TextUtils.isEmpty(text)){
          String id = reference.push().getKey();

          Query query = new Query(id, text);

          reference.child(id).setValue(query);
          Toast.makeText(this,"Raised Query Successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Please enter a query",Toast.LENGTH_SHORT).show();
        }
    }
}
