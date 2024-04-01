package com.fyp.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignIn extends AppCompatActivity {

    Button btnLogin;
    EditText etRegEmail;
    EditText etRegPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etRegEmail =(EditText)findViewById(R.id.etRegEmail);
        etRegPassword =(EditText) findViewById(R.id.etRegPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etRegEmail.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etRegEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etRegPassword.setError("Password is required");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                //Authenticate user

                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivitySignIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ActivityHome.class));
                        } else {
                            Toast.makeText(ActivitySignIn.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
