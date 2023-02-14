package com.example.findaval.UI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomSQLiteQuery;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.findaval.R;
import com.example.findaval.databinding.ActivityProfile2Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    ActivityProfile2Binding binding;
 ActivityResultLauncher<String>activityResultLauncher;
 ActivityResultLauncher<Intent>activityResultIntent;
 Bitmap bitmap;
 Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfile2Binding.inflate(getLayoutInflater());

       setContentView(binding.getRoot());



       binding.add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              image();
           }
       });


    }

    private void image() {

        String[] items = {"Camera", "Library"};

        AlertDialog.Builder alerDialog = new AlertDialog.Builder(Profile.this);

        View view = LayoutInflater.from(Profile.this).inflate(R.layout.image_choice, null);
        alerDialog.setView(view);

        alerDialog.show();

        FloatingActionButton camera = view.findViewById(R.id.camera);
        FloatingActionButton gallery = view.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Profile.this, "Camera", Toast.LENGTH_SHORT).show();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Profile.this, "Gallery", Toast.LENGTH_SHORT).show();
            }
        });


    }
}