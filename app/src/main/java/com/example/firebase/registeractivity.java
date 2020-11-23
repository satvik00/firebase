package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registeractivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button submit;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        email=findViewById(R.id.textView);
        password=findViewById(R.id.textView1);
        submit=findViewById(R.id.button3);
        auth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext= email.getText().toString();
                String passwordtext=password.getText().toString();
                if(TextUtils.isEmpty(emailtext) || TextUtils.isEmpty(passwordtext)){
                    Toast.makeText(registeractivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else if(passwordtext.length()<6){
                    Toast.makeText(registeractivity.this, "password too short", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(emailtext,passwordtext);
                }
            }
        });
    }

    private void registerUser(String emailtext, String passwordtext) {
        auth.createUserWithEmailAndPassword(emailtext,passwordtext).addOnCompleteListener(registeractivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registeractivity.this, "Registering user successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registeractivity.this,MainActivity.class));
                }else{
                    Toast.makeText(registeractivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}