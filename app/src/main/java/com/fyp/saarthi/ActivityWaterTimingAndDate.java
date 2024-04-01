package com.fyp.saarthi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityWaterTimingAndDate extends AppCompatActivity implements View.OnClickListener {
    EditText area,date,time;
    FirebaseFirestore db;
    Button btnDatePicker, btnTimePicker,btnList;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_timing_and_date);

        btnList=(Button)findViewById(R.id.btnList);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        area=(EditText)findViewById(R.id.area);

        btnDatePicker.setOnClickListener((View.OnClickListener) this);
        btnTimePicker.setOnClickListener((View.OnClickListener) this);
        db=FirebaseFirestore.getInstance();

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityWaterTimingAndDate.this,ActivityAdminDisplayWaterList.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
    public void add(View v){
        waterTimingModelAdmin waterTimingModelAdmin= new waterTimingModelAdmin(area.getText().toString(),date.getText().toString(),time.getText().toString());
        db.collection("waterDateAndTime").document().set(waterTimingModelAdmin)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data stored successfully",Toast.LENGTH_LONG).show();
                            area.setText(" ");
                            date.setText(" ");
                            time.setText(" ");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

