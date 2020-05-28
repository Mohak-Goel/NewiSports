package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateEvent2 extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private ImageView imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);

        imageSelected = findViewById(R.id.imageView);

        findViewById(R.id.pickImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            CreateEvent2.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else {
                    selectImage();
                }
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

        }
    }


    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageSelected.setImageBitmap(bitmap);

                        File selectedImageFile = new File(getPathFromUri(selectedImageUri));

                    } catch (Exception exception) {
                        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPathFromUri(Uri contentUri) {
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri, null, null, null, null);
        if (cursor == null) {
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
}
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

