package com.kamalclasses.m_healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PsychCheritaActivity extends AppCompatActivity {

    Button btnCallPsychCherita, btnMsgPsychCherita, btnLocPsychCherita;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psych_cherita);

        btnCallPsychCherita = findViewById(R.id.btnCallPsychCherita);
        btnMsgPsychCherita = findViewById(R.id.btnMsgPsychCherita);
        btnLocPsychCherita = findViewById(R.id.btnLocPsychCherita);

        btnCallPsychCherita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = ActivityCompat.checkSelfPermission(PsychCheritaActivity.this, Manifest.permission.CALL_PHONE);
                if(res == PackageManager.PERMISSION_GRANTED){
                    String p = "9136911957";
                    Intent a = new Intent(Intent.ACTION_CALL);
                    a.setData(Uri.parse("tel:" + p));
                    startActivity(a);
                }
                else{
                    ActivityCompat.requestPermissions(PsychCheritaActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }
            }
        });
        btnMsgPsychCherita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = ActivityCompat.checkSelfPermission(PsychCheritaActivity.this, Manifest.permission.SEND_SMS);
                if(res == PackageManager.PERMISSION_GRANTED){
                    String p = "9136911957";
                    sharedPreferences = getSharedPreferences("monica", MODE_PRIVATE);
                    String name = sharedPreferences.getString("name", "");
                    String age = sharedPreferences.getString("age", "");
                    String diseases = sharedPreferences.getString("diseases", "");
                    String medication = sharedPreferences.getString("medication", "");
                    String weight = sharedPreferences.getString("weight", "");
                    String m = "Hi doctor, my name is " + name + "\nMy age is " + age + "\nDiseases: " + diseases + "\nMedications: " + medication + "\nweight: " + weight;
                    String m2 = "I want to book appointment/enquiry for consultation, let me know the confirmation details at the earliest\nThankyou";
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(p, null, m ,null, null);
                    smsManager.sendTextMessage(p, null, m2, null, null );
                    Toast.makeText(PsychCheritaActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    ActivityCompat.requestPermissions(PsychCheritaActivity.this, new String[]{Manifest.permission.SEND_SMS}, 145);
                }
            }
        });
        btnLocPsychCherita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = "59, Lotus building, Mohammad Ali Road, 1st Floor, Office No 10, opposite Union Bank, Mumbai, Maharashtra 400003";
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse("geo:0,0?q=" + location));
                startActivity(a);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ((requestCode == 123) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            String p = "9136911957";
            Intent a = new Intent(Intent.ACTION_CALL);
            a.setData(Uri.parse("tel:" + p));
            startActivity(a);
        }
        if ((requestCode == 145) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            String p = "9136911957";
            sharedPreferences = getSharedPreferences("monica", MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "");
            String age = sharedPreferences.getString("age", "");
            String diseases = sharedPreferences.getString("diseases", "");
            String medication = sharedPreferences.getString("medication", "");
            String weight = sharedPreferences.getString("weight", "");
            String m = "Hi doctor, my name is " + name + "\nMy age is " + age + "\nDiseases: " + diseases + "\nMedications: " + medication + "\nweight: " + weight;
            String m2 = "I want to book appointment/enquiry for consultation, let me know the confirmation details at the earliest\nThankyou";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(p, null, m ,null, null);
            smsManager.sendTextMessage(p, null, m2, null, null );
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        }
    }

}