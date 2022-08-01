package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;

public class DetailsActivity extends AppCompatActivity {

EditText etSharedName, etSharedAge, etSharedDiseases, etSharedMedication, etSharedWeight;
Button btnSave;
SharedPreferences sharedPreferences;
RadioGroup RgGender;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        etSharedName= findViewById(R.id.etSharedName);
        etSharedAge = findViewById(R.id.etSharedAge);
        etSharedDiseases = findViewById(R.id.etSharedDiseases);
        etSharedMedication = findViewById(R.id.etSharedMedication);
        etSharedWeight = findViewById(R.id.etSharedWeight);
        btnSave = findViewById(R.id.btnSave);
        RgGender = findViewById(R.id.RgGender);
        firebaseAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences("monica", MODE_PRIVATE);

        String name =sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String diseases = sharedPreferences.getString("diseases", "");
        String medication = sharedPreferences.getString("medication", "");
        String weight = sharedPreferences.getString("weight", "");
        String gender = sharedPreferences.getString("gender", "");

            if(name.length() == 0 | age.length()==0 | diseases.length()==0 | medication.length()==0 | weight.length()==0 | gender.length() == 0){
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = etSharedName.getText().toString();
                        if(name.length() == 0){
                            etSharedName.setError("Name Cannot be empty");
                            return;
                        }
                        String age = etSharedAge.getText().toString();
                        if(age.length() == 0){
                            etSharedAge.setError("Age cannot be empty");
                        }
                        String diseases = etSharedDiseases.getText().toString();
                        if(diseases.length()==0){
                            etSharedDiseases.setError("Diseases cannot be empty, write NA if not applicable");
                        }
                        String medication = etSharedMedication.getText().toString();
                        if(medication.length() == 0){
                            etSharedMedication.setError("Medication cannot be empty, write NA if not applicable");
                        }
                        String weight = etSharedWeight.getText().toString();
                        if(weight.length() == 0){
                            etSharedWeight.setError("Weight cannot be empty");
                        }
                        else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", name);
                            editor.putString("age", age);
                            editor.putString("diseases", diseases);
                            editor.putString("medication", medication);
                            editor.putString("weight", weight);
                            editor.commit();
                            Intent a = new Intent(DetailsActivity.this, ServicesActivity.class);
                            startActivity(a);
                            finish();
                        }

                    }
                });
            }
            else {
                Intent a = new Intent(DetailsActivity.this, ServicesActivity.class);
                startActivity(a);
                finish();
            }





    }
}