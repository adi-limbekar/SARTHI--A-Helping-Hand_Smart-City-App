package com.fyp.saarthi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ActivityCovid19 extends AppCompatActivity {

    CardView cvCovid19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);

        cvCovid19 = findViewById(R.id.cvCovid19);

        cvCovid19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.covid19india.org/"));
                startActivity(browserIntent);
            }
        });
    }
}
