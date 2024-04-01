package com.fyp.saarthi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private long backPressedTime;
    private Toast backToast;
    CardView cvBus;
    CardView cvGarbage;
    CardView cvNearby;
    CardView cvEmergency;
    CardView cvWater;
    CardView cvElectricity;
    CardView cvCovid;

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isServicesOK()){
                    init();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateProfile();

        cvBus = findViewById(R.id.cvBus);
        cvGarbage = findViewById(R.id.cvGarbage);
        cvNearby = findViewById(R.id.cvNearby);
        cvEmergency = findViewById(R.id.cvEmergency);
        cvWater = findViewById(R.id.cvWater);
        cvElectricity = findViewById(R.id.cvElectricity);
        cvCovid = findViewById(R.id.cvCovid);

        cvBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentBusTracking fragmentBusTracking = new FragmentBusTracking();
                fragmentTransaction.replace(R.id.mainActivity,fragmentBusTracking).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        cvGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityGarbage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        cvNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,NearbyPlacesActivity.class);
                startActivity(intent);
            }
        });

        cvEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityEmergencyNumbers.class);
                startActivity(intent);
            }
        });

        cvWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                WaterDept waterDept = new WaterDept();
                fragmentTransaction.replace(R.id.mainActivity,waterDept).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        cvElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        cvCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityCovid19.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(backPressedTime + 2000 > System.currentTimeMillis() ){
            backToast.cancel();
            super.onBackPressed();
            return;

        }else{
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),ActivityLogin.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

        } else if (id == R.id.nav_nearbyPlaces) {
            Intent intent = new Intent(ActivityHome.this,NearbyPlacesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_waterDept) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            WaterDept waterDept = new WaterDept();
            fragmentTransaction.replace(R.id.mainActivity,waterDept).addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_ElectricityDept) {
            Intent intent = new Intent(ActivityHome.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            return true;

        } else if (id == R.id.nav_EmergencyNo) {
            Intent intent = new Intent(ActivityHome.this,ActivityEmergencyNumbers.class);
            startActivity(intent);

        } else if (id == R.id.nav_BusTracking) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentBusTracking fragmentBusTracking = new FragmentBusTracking();
            fragmentTransaction.replace(R.id.mainActivity,fragmentBusTracking).addToBackStack(null);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_garbageReports) {
            Intent intent = new Intent(ActivityHome.this,ActivityGarbage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }else if (id == R.id.nav_Support) {

        } else if (id == R.id.nav_AboutUs) {

        }
        else if (id == R.id.nav_Logout) {

            GoogleSignInClient mGoogleSignInClient ;
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
            mGoogleSignInClient.signOut().addOnCompleteListener(ActivityHome.this,
                    new OnCompleteListener<Void>() {  //signout Google
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            FirebaseAuth.getInstance().signOut(); //signout firebase
                            Intent setupIntent = new Intent(getBaseContext(),ActivityLogin.class);
                            Toast.makeText(getBaseContext(), "Logged Out", Toast.LENGTH_LONG).show(); //if u want to show some text
                            setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(setupIntent);
                            finish();
                        }
                    });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateProfile(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tvUsername);
        TextView navUseremail = headerView.findViewById(R.id.tvUserEmail);
        ImageView navUserImage = (ImageView) headerView.findViewById(R.id.imageUser);

        navUsername.setText(currentUser.getDisplayName());
        navUseremail.setText(currentUser.getEmail());

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void init(){
                Intent intent = new Intent(ActivityHome.this, MapsActivity.class);
                startActivity(intent);
            }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ActivityHome.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(ActivityHome.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}