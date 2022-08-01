package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.sql.Time;

public class SleepTrackerActivity extends AppCompatActivity {

    Chronometer sleeptimechronometer;
    long time;
    Button btnStart, btnPause, btnStop;
    private boolean isRunning = false;
    private long pauseOffset = 0;
    Switch switchAlarm, switchSnooze;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);

        sleeptimechronometer = findViewById(R.id.sleeptimechronometer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        switchAlarm = (Switch) findViewById(R.id.switchAlarm);
        switchSnooze =(Switch) findViewById(R.id.switchSnooze);

        sleeptimechronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(((SystemClock.elapsedRealtime() - sleeptimechronometer.getBase()) >= 6000) && ((SystemClock.elapsedRealtime() - sleeptimechronometer.getBase()) <= 7000)) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(SleepTrackerActivity.this, R.raw.happyplacealarmaudio);
                        mediaPlayer.start();
                        if(mediaPlayer.isPlaying()){
                            switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                    if(switchAlarm.isChecked() == true){
                                        switchSnooze.setChecked(false);
                                        mediaPlayer.stop();
                                        sleeptimechronometer.setBase(SystemClock.elapsedRealtime());
                                        sleeptimechronometer.stop();
                                        pauseOffset = 0;
                                        isRunning = false;
                                    }
                                }
                            });
                            switchSnooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                    mediaPlayer.stop();
                                    switchAlarm.setChecked(false);
                                }
                            });
                        }
                        Toast.makeText(SleepTrackerActivity.this, "8 hours of sleep completed", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning){
                    sleeptimechronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    sleeptimechronometer.start();
                    isRunning = true;
                    btnStart.setText("Start");
                    switchAlarm.setChecked(false);
                    switchSnooze.setChecked(false);
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning){
                    sleeptimechronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - sleeptimechronometer.getBase();
                    isRunning = false;
                    btnStart.setText("Resume");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleeptimechronometer.setBase(SystemClock.elapsedRealtime());
                sleeptimechronometer.stop();
                pauseOffset = 0;
                isRunning = false;

            }
        });
    }
}