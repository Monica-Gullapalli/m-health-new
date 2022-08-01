package com.kamalclasses.m_healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText etMainUsername, etMainPassword;
    Button btnLogin, btnSignUp;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMainUsername = findViewById(R.id.etMainUsername);
        etMainPassword = findViewById(R.id.etMainPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            Intent a = new Intent(MainActivity.this, ServicesActivity.class);
            startActivity(a);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = etMainUsername.getText().toString();
                String pw = etMainPassword.getText().toString();
                Task t = firebaseAuth.signInWithEmailAndPassword(un, pw);
                t.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Intent a = new Intent(MainActivity.this, DetailsActivity.class);
                            startActivity(a);
                            finish();
                        }
                        else{
                            Exception err_msg = task.getException();
                            Toast.makeText(MainActivity.this, String.valueOf(err_msg), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(a);
            }
        });
    }
}