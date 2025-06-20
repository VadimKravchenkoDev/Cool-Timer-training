package com.kravchenkovadim.cooltimerkotlin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            new SettingsFragment()
                    ).commit();
        }
    }
}
