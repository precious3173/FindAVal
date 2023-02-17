package com.example.findaval.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.RoomSQLiteQuery;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.findaval.Database.ImageConverter;
import com.example.findaval.Database.ProfileDatabase;
import com.example.findaval.Database.ProfileEntity;
import com.example.findaval.R;
import com.example.findaval.databinding.ActivityProfile2Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    ActivityProfile2Binding binding;
 ActivityResultLauncher<String>activityResultLauncher;
 ActivityResultLauncher<Intent>activityResultIntent;
 Bitmap bitmap;
 Uri uri;
 Intent openLibraryIntent;
 ProfileDatabase profileDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfile2Binding.inflate(getLayoutInflater());

       setContentView(binding.getRoot());


   activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
       @Override
       public void onActivityResult(Boolean result) {

           if(result) {
               Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
           } else {
               Toast.makeText(getApplicationContext(), "Permission not granted", Toast.LENGTH_SHORT).show();
           }
       }
   });


       binding.add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              image();
           }
       });


       activityResultIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
           @Override
           public void onActivityResult(ActivityResult result) {

               if(result.getResultCode() == Activity.RESULT_OK){
             openLibraryIntent = result.getData();
             uri = openLibraryIntent.getData();

             if(uri != null){
                 try {
                     bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                     binding.profile.setImageBitmap(bitmap);

                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
               }
           }
       });
    }

    private void image() {


        AlertDialog.Builder alerDialog = new AlertDialog.Builder(Profile.this);

        View view = LayoutInflater.from(Profile.this).inflate(R.layout.image_choice, null);
        alerDialog.setView(view);

        alerDialog.show();

        FloatingActionButton camera = view.findViewById(R.id.camera);
        FloatingActionButton gallery = view.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    activityResultLauncher.launch(Manifest.permission.CAMERA);
                }
                else{
                    openCamera();
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    openLibrary();
                }
            }
        });


    }

    private void openLibrary() {
        openLibraryIntent = new Intent(
                Intent.ACTION_PICK
        );
        openLibraryIntent.setType("image/*");
        activityResultIntent.launch(openLibraryIntent);

    }

    private void openCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Profile Image");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uri = intent.getData();
        intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, uri);
        activityResultIntent.launch(intent);

    }
}