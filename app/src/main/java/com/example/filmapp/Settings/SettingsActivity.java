package com.example.filmapp.Settings;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.filmapp.R;

public class SettingsActivity extends AppCompatActivity {

    /* Starts when activity is created*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        /*Menu bar */
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            /* goes one page back */
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /* Decides if the click is consumed */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}