package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEvent2 extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    //ImageView imageSelected;
    //private EditText urlLink;
    Button ch, up;
    ImageView img;
    StorageReference mstorageRef;
    public Uri imguri;

    //Button eventCreated;

    //private Uri selectedImageUri;

    CreateEvent1Form createEvent1Form;
    //FirebaseDatabase storage;
    //Storage storagereference;

    //private StorageReference mstorageRef;
    //private DatabaseReference mdatabaseRef;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference reference2;
    FirebaseUser user_id;
    String uid_user;
    long id_user;
    long ev_details;

    RadioGroup Food, Lodging, Transport;
    RadioButton ProvideTransport, ProvideFood, ProvideLodging;
    EditText  EventDescription, OurContact;
    TextView   tvUrl;
    EditText EventName, FieldLocation, CityName, PostalCode, SportsName, etDate, chooseTime;
    Button buttonCreate;



    //FirebaseDatabase database;
    //DatabaseReference ref;
    CreateEvent createEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        mstorageRef = FirebaseStorage.getInstance().getReference("My Events");


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
        uid_user=user_id.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("My Events Created").child(uid_user);
        reference2=FirebaseDatabase.getInstance().getReference().child("Event Details");
        //mstorageRef = FirebaseStorage.getInstance().getReference("Event Created");
        //mdatabaseRef = FirebaseDatabase.getInstance().getReference("Event Created");


       // createEvent = (CreateEvent) getIntent().getSerializableExtra("CreateEvent1 Data");







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



        // database = FirebaseDatabase.getInstance();
        //ref = database.getReference("Event Created");


        //findViewById(R.id.pickPoster).setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {

        //  if (ContextCompat.checkSelfPermission(
        //        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
        //) != PackageManager.PERMISSION_GRANTED) {
        //  ActivityCompat.requestPermissions(
        //        CreateEvent2.this,
        //      new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
        //    REQUEST_CODE_STORAGE_PERMISSION
        //);
        //} else {
        //  selectImage();
        //}
        //}
        //});
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    id_user = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("My Events");

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
                        food, lodging, transport, EventDescription.getText().toString(), OurContact.getText().toString(),tvUrl.getText().toString());
                reference.child(String.valueOf(id_user+1)).setValue(createEvent);
                reference2.child(String.valueOf(ev_details+1)).setValue(createEvent);
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
        StorageReference Ref = mstorageRef.child(OurContact.getText().toString());

        Ref.child(OurContact.getText().toString()).putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                       //Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();

                        //if(downloadUri.isSuccessful()){
                          //  generatedFilePath = downloadUri.getResult().toString();
                           // System.out.println("## Stored path is "+generatedFilePath);
                        //}
                        Toast.makeText(CreateEvent2.this, "Image uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        Task<Uri> downloadUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        String ImageURL=downloadUrl.toString();
                        tvUrl.setText(ImageURL);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        }

}


    //private void selectImage() {
      //  Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //if (intent.resolveActivity(getPackageManager()) != null) {
          //  startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

    //    }
    //}

   // private void uploadFile() {

   //     if (selectedImageUri != null) {
     //       StorageReference fileReference = mstorageRef.child(System.currentTimeMillis()
       //             + "." + getFileExtension(selectedImageUri));

         //   fileReference.putFile(selectedImageUri)
           //         .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             //           @Override
               //         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                 //           Toast.makeText(CreateEvent2.this, "Upload Successful", Toast.LENGTH_LONG).show();
                   //         CreateEvent upload = new CreateEvent(urlLink.getText().toString().trim(),
                     //               taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                       //     String uploadId = mdatabaseRef.push().getKey();
                         //   mdatabaseRef.child(uploadId).setValue(upload);
                        //}
                    //})
                    //.addOnFailureListener(new OnFailureListener() {
                      //  @Override
                        //public void onFailure(@NonNull Exception e) {
                          //  Toast.makeText(CreateEvent2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        //}
                    //});
            // .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            //   @Override
            // public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

            // }
            // });
        //} else {
          //  Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        //}
    //}

   // private String getPathFromUri(Uri contentUri) {
     //   String filePath;
       // Cursor cursor = getContentResolver()
         //       .query(contentUri, null, null, null, null);
       // if (cursor == null) {
         //   filePath = contentUri.getPath();
        //} else {
          //  cursor.moveToFirst();
            //int index = cursor.getColumnIndex("_data");
            //filePath = cursor.getString(index);
            //cursor.close();
        //}
        //return filePath;
    //}

    //private String getFileExtension(Uri uri) {
      //  ContentResolver cR = getContentResolver();
        //MimeTypeMap mime = MimeTypeMap.getSingleton();
       // return mime.getExtensionFromMimeType(cR.getType(uri));
    //}

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
          //  if (data != null) {
            //    selectedImageUri = data.getData();
              //  if (selectedImageUri != null) {
                //    try {
                  //      InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    //    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                      //  imageSelected.setImageBitmap(bitmap);

                        //File selectedImageFile = new File(getPathFromUri(selectedImageUri));

                    //} catch (Exception exception) {
                      //  Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    //}
                //}
            //}
        //}
    //}

    //public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
          //  if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //    selectImage();
            //} else {
              //  Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            //}
        //}
    //}

   //     Button pickImage=findViewById(R.id.pickImages);

     //   pickImage.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {

           //     if(ActivityCompat.checkSelfPermission(CreateEvent2.this,
             //           Manifest.permission.READ_EXTERNAL_STORAGE)
               //         != PackageManager.PERMISSION_GRANTED) {
                 //   ActivityCompat.requestPermissions(CreateEvent2.this,
                   //         new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                     //       100);
                    //return;
                //}
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                //intent.setType("image/*");
                //startActivityForResult(intent, 1);
            //}
        //});
    //}

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1 && resultCode == RESULT_OK) {
          //  final ImageView imageView = findViewById(R.id.imageView);
            //final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();
            //if(clipData != null){
              //  for(int i=0;i<clipData.getItemCount();i++){
                //    Uri imageUri = clipData.getItemAt(i).getUri();
                  //  try{
                    //    InputStream is = getContentResolver().openInputStream(imageUri);
                      //  Bitmap bitmap = BitmapFactory.decodeStream(is);
                        //bitmaps.add(bitmap);
                   // } catch (FileNotFoundException e){
                     //   e.printStackTrace();
                    //}
                //}
                
                //new Thread(new Runnable() {
                  //  @Override
                    //public void run() {
                      //  for(final Bitmap b : bitmaps){
                        //    runOnUiThread(new Runnable() {
                          //      @Override
                            //    public void run() {
                              //      imageView.setImageBitmap(b);
                                //}
                            //});

                            //try{
                              //  Thread.sleep(3000);
                            //}catch(InterruptedException e){
                              //  e.fillInStackTrace();
                            //}
                        //}
                    //}
                //}).start();
            //}

        //}
    //}

