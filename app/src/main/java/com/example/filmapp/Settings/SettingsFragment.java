package com.example.filmapp.Settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.filmapp.R;


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
