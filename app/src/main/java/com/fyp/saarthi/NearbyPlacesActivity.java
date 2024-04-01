package com.fyp.saarthi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class NearbyPlacesActivity extends AppCompatActivity {
    private static final String TAG = "NearbyPlaveActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    CardView cvNearby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);

        cvNearby = findViewById(R.id.cvNearby);

        if (isServicesOK()) {
            init();
        }
    }

    public void init(){

        cvNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyPlacesActivity.this,NearbyMapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOK(){
        Log.d(TAG,"isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(NearbyPlacesActivity.this);
        if(available == ConnectionResult.SUCCESS){
            //everything is fine
            Log.d(TAG,"isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG,"isServicesOK: an error occured but can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(NearbyPlacesActivity.this,available,ERROR_DIALOG_REQUEST);

            dialog.show();
        }
        else {
            Toast.makeText(this,"We cant make map request",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
