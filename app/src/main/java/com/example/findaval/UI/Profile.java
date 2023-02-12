package com.example.findaval.UI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomSQLiteQuery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.findaval.R;
import com.example.findaval.databinding.ActivityProfile2Binding;

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

        AlertDialog.Builder alerDialog = new AlertDialog.Builder(getApplicationContext());

        View view = LayoutInflater.from(getApplicationContext()).inflate()
    }
}