package com.fyp.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAdminLogin extends AppCompatActivity {

    EditText etRegAdUsername;
    EditText etRegAdPassword;
    Button btnAdLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etRegAdUsername = (EditText)findViewById(R.id.etRegAdUsername);
        etRegAdPassword = (EditText) findViewById(R.id.etRegAdPassword);
        btnAdLogin = (Button) findViewById(R.id.btnAdLogin);

        btnAdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etRegAdUsername.getText().toString().equals("AdminWaterDepartment") && etRegAdPassword.getText().toString().equals("Maps@1234"))
                     {
                        Intent intent = new Intent(ActivityAdminLogin.this, ActivityWaterDepartmentAdmin.class);
                        startActivity(intent);
                        finish();
                    }
                else if (etRegAdUsername.getText().toString().equals("AdminGarbageDepartment") && etRegAdPassword.getText().toString().equals("Spam@4321"))
                {
                    startActivity(new Intent(ActivityAdminLogin.this,ActivityAdminGarbageComplaints.class));
                    finish();
                }
                else{
                    Toast.makeText(ActivityAdminLogin.this,"Enter valid Credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
