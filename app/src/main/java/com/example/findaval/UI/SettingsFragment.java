package com.example.findaval.UI;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.findaval.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}