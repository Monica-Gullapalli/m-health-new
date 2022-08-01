package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookAppointmentActivity extends AppCompatActivity {

    Button btnUseDocs, btnFindDocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        btnUseDocs = findViewById(R.id.btnUseDocs);
        btnFindDocs = findViewById(R.id.btnFindDocs);

        btnUseDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(BookAppointmentActivity.this, UseDocsActivity.class);
                startActivity(a);
            }
        });

        btnFindDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(BookAppointmentActivity.this, FindDocActivity.class);
                startActivity(a);
            }
        });

    }
}