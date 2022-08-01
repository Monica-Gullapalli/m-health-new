package com.kamalclasses.m_healthapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewCalorieActivity extends AppCompatActivity {

    ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calorie);

        lvData = findViewById(R.id.lvData);

        ArrayList<food> data = CalorieTrackerActivity.dbHandler.view();
        if(data.size() > 0){
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
            lvData.setAdapter(arrayAdapter);
        }
    }
}
