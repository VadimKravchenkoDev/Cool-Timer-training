package com.kravchenkovadim.cooltimerkotlin;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private Button button;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(59);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            long l = progress*1000;
                updateTimer(l);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        button.setOnClickListener(view -> {
            CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress()*1000,1000) {
                @Override
                public void onTick(long l) {
                    updateTimer(l);
                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell_sound);
                    mediaPlayer.start();
                }
            }.start();
        });
    }
    private void updateTimer(long millisec){
        int minute = (int) millisec/1000/60;
        int second = (int) millisec/1000-minute*60;
        String stMinute=""+minute;
        String stSecond=""+second;
        if(minute<10){
            stMinute = "0" + minute;
        }
        if(second<10){
            stSecond = "0" + second;
        }
        textView.setText(stMinute + ":"+ stSecond);
    }
}