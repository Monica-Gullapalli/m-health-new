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

public class PedMonActivity extends AppCompatActivity {

    Button btnCallPedMon, btnMsgPedMon, btnLocPedMon;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_mon);

        btnCallPedMon = findViewById(R.id.btnCallPedMon);
        btnMsgPedMon = findViewById(R.id.btnMsgPedMon);
        btnLocPedMon = findViewById(R.id.btnLocPedMon);

        btnCallPedMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = ActivityCompat.checkSelfPermission(PedMonActivity.this, Manifest.permission.CALL_PHONE);
                if(res == PackageManager.PERMISSION_GRANTED){
                    String p = "7710894424";
                    Intent a = new Intent(Intent.ACTION_CALL);
                    a.setData(Uri.parse("tel:" + p));
                    startActivity(a);
                }
                else{
                    ActivityCompat.requestPermissions(PedMonActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }
            }
        });
        btnMsgPedMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = ActivityCompat.checkSelfPermission(PedMonActivity.this, Manifest.permission.SEND_SMS);
                if(res == PackageManager.PERMISSION_GRANTED){
                    String p = "8097528440";
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
                    Toast.makeText(PedMonActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    ActivityCompat.requestPermissions(PedMonActivity.this, new String[]{Manifest.permission.SEND_SMS}, 145);
                }
            }
        });
        btnLocPedMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = "White Hall Buliding, August Kranti Rd, Kemps Corner, Malabar Hill, Mumbai, Maharashtra 400036";
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
            String p = "7710894424";
            Intent a = new Intent(Intent.ACTION_CALL);
            a.setData(Uri.parse("tel:" + p));
            startActivity(a);
        }
        if ((requestCode == 145) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            String p = "7710894424";
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