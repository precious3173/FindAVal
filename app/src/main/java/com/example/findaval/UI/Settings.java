package com.example.findaval.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.findaval.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();

    }
}