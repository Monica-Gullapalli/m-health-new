package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindDocActivity extends AppCompatActivity {

    EditText etSearchDoctor;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc);
        etSearchDoctor = findViewById(R.id.etSearchDoctor);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = etSearchDoctor.getText().toString();
                if(location.length() == 0){
                    etSearchDoctor.setError("Invalid Location");
                    return;
                }
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse("geo:0,0?q=" + location));
                startActivity(a);
            }
        });
    }
}