package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ServicesActivity extends AppCompatActivity {

    TextView tvMSG;
    Button btnMedRem, btnSleepTracker, btnCalorieTracker, btnBookApp, btnLogout, btnStepCount, btnCoronaCount;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        tvMSG = findViewById(R.id.tvMSG);
        btnMedRem = findViewById(R.id.btnMedRem);
        btnSleepTracker = findViewById(R.id.btnSleepTracker);
        btnCalorieTracker = findViewById(R.id.btnCalorieTracker);
        btnBookApp = findViewById(R.id.btnBookApp);
        btnStepCount = findViewById(R.id.btnStepCount);
        btnLogout = findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        btnCoronaCount = findViewById(R.id.btnCoronaCount);

        sharedPreferences =getSharedPreferences("monica", MODE_PRIVATE);
        String name =sharedPreferences.getString("name", "");
        tvMSG.setText("Welcome " + name);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent a = new Intent(ServicesActivity.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });

        btnMedRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, MedRemActivity.class);
                startActivity(a);
            }
        });

        btnSleepTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, SleepTrackerActivity.class);
                startActivity(a);
            }
        });

        btnCalorieTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, CalorieTrackerActivity.class);
                startActivity(a);
            }
        });

        btnBookApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, BookAppointmentActivity.class);
                startActivity(a);
            }
        });

        btnStepCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, StepCounterActivity.class);
                startActivity(a);
            }
        });

        btnCoronaCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ServicesActivity.this, CoronaCountActivity.class);
                startActivity(a);
            }
        });

    }
}