package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button submit;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        email=findViewById(R.id.textView2);
        password=findViewById(R.id.textView3);
        submit=findViewById(R.id.button4);
        auth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textemail=email.getText().toString();
                String textpassword=password.getText().toString();
                loginuser(textemail,textpassword);
            }
        });
    }

    private void loginuser(String e_mail, String pass) {
        auth.signInWithEmailAndPassword(e_mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(loginactivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginactivity.this,MainActivity.class));
                finish();
            }
        });
    }
}