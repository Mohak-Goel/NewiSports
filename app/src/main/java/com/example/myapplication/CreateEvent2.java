package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreateEvent2 extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    Button ch, up;
    ImageView img;
    StorageReference mstorageRef;
    public Uri imguri;


    CreateEvent1Form createEvent1Form;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference reference2;
    FirebaseUser user_id;
    String uid_user;
    long ev_details;

    RadioGroup Food, Lodging, Transport;
    RadioButton ProvideTransport, ProvideFood, ProvideLodging;
    EditText  EventDescription, OurContact;
    TextView   tvUrl;
    EditText EventName, FieldLocation, CityName, PostalCode, SportsName, etDate, chooseTime;
    Button buttonCreate;


    CreateEvent createEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        mstorageRef = FirebaseStorage.getInstance().getReference("Event Details");


        EventName = findViewById(R.id.eventName);
        FieldLocation = findViewById(R.id.fieldLoc);
        CityName = findViewById(R.id.cityName);
        PostalCode = findViewById(R.id.postalCode);
        etDate = findViewById(R.id.et_date);
        chooseTime = findViewById(R.id.etChooseTime);
        SportsName = findViewById(R.id.sportsName);
        Food = findViewById(R.id.provideFood);
        Lodging = findViewById(R.id.provideLodging);
        Transport = findViewById(R.id.provideTransport);
        ch = findViewById(R.id.btnChoose);
        img = findViewById(R.id.imgview);
        up = findViewById(R.id.btnUpload);
        EventDescription = findViewById(R.id.eventdescrip);
        OurContact = findViewById(R.id.ourContact);
        tvUrl=findViewById(R.id.url_text);
        buttonCreate = findViewById(R.id.create_Event);
        createEvent1Form = (CreateEvent1Form) getIntent().getSerializableExtra("CreateEvent1 Data");


        user_id= FirebaseAuth.getInstance().getCurrentUser();
        assert user_id != null;
        uid_user=user_id.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("My Events Created").child(uid_user);
        reference2=FirebaseDatabase.getInstance().getReference().child("Event Details");


        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filechooser();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fileuploader();
            }
        });



        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    ev_details = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   rootNode = FirebaseDatabase.getInstance();
             //   reference = rootNode.getReference("My Events");

                int select = Food.getCheckedRadioButtonId();
                ProvideFood = (RadioButton) findViewById(select);
                boolean food = true;
                if (ProvideFood.getId() == R.id.food_no) food = false;

                select = Lodging.getCheckedRadioButtonId();
                ProvideLodging = (RadioButton) findViewById(select);
                boolean lodging = true;
                if (ProvideLodging.getId() == R.id.lodging_no) lodging = false;

                select = Transport.getCheckedRadioButtonId();
                ProvideTransport = (RadioButton) findViewById(select);
                boolean transport = true;
                if (ProvideTransport.getId() == R.id.transport_no) transport = false;

                createEvent = new CreateEvent(createEvent1Form.getEventName(), createEvent1Form.getFieldName(), createEvent1Form.getCityName(),
                        createEvent1Form.getPostalCode(), createEvent1Form.getSportsName(), createEvent1Form.getChooseTime(), createEvent1Form.getEtDate(),
                        food, lodging, transport, EventDescription.getText().toString(), OurContact.getText().toString(),tvUrl.getText().toString(), "Upcoming");
                reference.child(String.valueOf(ev_details+1)).setValue(createEvent);
                reference2.child(String.valueOf(ev_details+1)).setValue(createEvent);
                Toast.makeText(getApplicationContext(),"Event Created Successfully", Toast.LENGTH_SHORT).show();
                finish();
                //uploadFile();

            }
        });
    }

    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
        }
    }


    private void Filechooser () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    private String getExtension (Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader ()
    {
        final StorageReference Ref = mstorageRef.child(String.valueOf(ev_details+1));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(CreateEvent2.this, "Image uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                                String ImageURL= String.valueOf(uri);
                                tvUrl.setText(ImageURL);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });
        }

}

