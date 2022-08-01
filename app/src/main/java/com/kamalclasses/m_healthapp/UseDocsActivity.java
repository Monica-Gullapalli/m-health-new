package com.kamalclasses.m_healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class UseDocsActivity extends AppCompatActivity {

    Button btnDentistDad, btnENTMom, btnTherapistMonica, btnPsychCherita, btnPedMon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_docs);

        btnDentistDad = findViewById(R.id.btnDentistDad);
        btnENTMom = findViewById(R.id.btnENTMom);
        btnTherapistMonica = findViewById(R.id.btnTherapistMonica);
        btnPsychCherita = findViewById(R.id.btnPsychCherita);
        btnPedMon = findViewById(R.id.btnPedMon);


        btnDentistDad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UseDocsActivity.this, DentistDadActivity.class);
                startActivity(a);
            }
        });

        btnENTMom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UseDocsActivity.this, ENTMomActivity.class);
                startActivity(a);
            }
        });

        btnTherapistMonica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UseDocsActivity.this, TherapistMonicaActivity.class);
                startActivity(a);
            }
        });

        btnPsychCherita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UseDocsActivity.this, PsychCheritaActivity.class);
                startActivity(a);
            }
        });

        btnPedMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UseDocsActivity.this, PedMonActivity.class);
                startActivity(a);
            }
        });


    }

}