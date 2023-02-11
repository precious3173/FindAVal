package com.example.findaval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.findaval.Adapter.ViewPagerAdapter;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

          new Handler().postDelayed(new Runnable(){

              @Override
              public void run() {
                  Intent intent = new Intent(getApplicationContext(), ViewPagerAdapter.MainActivity.class);
                  startActivity(intent);
              }
          }, 2000);
    }
}