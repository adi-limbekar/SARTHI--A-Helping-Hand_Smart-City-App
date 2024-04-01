package com.fyp.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ActivityWaterDepartmentAdmin extends AppCompatActivity {

    CardView cvTiming;
    CardView cvTanker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_department_admin);

        cvTiming = findViewById(R.id.cvTiming);
        cvTanker = findViewById(R.id.cvTanker);

        cvTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityWaterDepartmentAdmin.this, ActivityWaterTimingAndDate.class);
                startActivity(myIntent);
            }
        });

        cvTanker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityWaterDepartmentAdmin.this, ActivityWaterTanker.class);
                startActivity(myIntent);
            }
        });
    }
}
