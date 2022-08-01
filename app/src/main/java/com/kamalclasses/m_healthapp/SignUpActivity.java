package com.kamalclasses.m_healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText etSignUpUsername, etSignUpPassword1, etSignUpPassword2;
    Button btnRegister, btnALogin;
    TextView tvSignUpMSG;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignUpUsername = findViewById(R.id.etSignUpUsername);
        etSignUpPassword1 = findViewById(R.id.etSignUpPassword1);
        etSignUpPassword2 = findViewById(R.id.etSignUpPassword2);
        btnRegister = findViewById(R.id.btnRegister);
        btnALogin = findViewById(R.id.btnALogin);
        tvSignUpMSG = findViewById(R.id.tvSignUpMSG);
        firebaseAuth = FirebaseAuth.getInstance();

        btnALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(c);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = etSignUpUsername.getText().toString();
                String pw1 = etSignUpPassword1.getText().toString();
                String pw2 = etSignUpPassword2.getText().toString();

                if(pw1.equals(pw2)){
                    Task t = firebaseAuth.createUserWithEmailAndPassword(un, pw1);
                    t.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){
                                Intent a = new Intent(SignUpActivity.this, DetailsActivity.class);
                                startActivity(a);
                                finish();
                            }
                            else{
                                Exception err_msg = task.getException();
                                Toast.makeText(SignUpActivity.this, String.valueOf(err_msg), Toast.LENGTH_SHORT).show();
                                tvSignUpMSG.setText(String.valueOf(err_msg));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}