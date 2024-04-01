package com.fyp.saarthi;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityWaterTanker extends AppCompatActivity {
    EditText tankName,tankNumber,area;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tanker);
        tankName=(EditText)findViewById(R.id.tankName);
        tankNumber=(EditText)findViewById(R.id.tankNumber);
        area=(EditText)findViewById(R.id.area);

        db=FirebaseFirestore.getInstance();
    }
    public void add(View v){
       waterAddTankerNumber waterAddTankerNumber= new waterAddTankerNumber(tankName.getText().toString(),tankNumber.getText().toString(),area.getText().toString());
       db.collection("waterTankNumbers").document().set(waterAddTankerNumber)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data stored successfully",Toast.LENGTH_LONG).show();
                            tankName.setText(" ");
                            tankNumber.setText(" ");
                            area.setText(" ");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
