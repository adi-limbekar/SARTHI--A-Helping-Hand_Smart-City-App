package com.fyp.saarthi;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "ActivityLogin";
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private Button btnSignIn;
    Button btnAdSignIn;
    TextView tvSignUp;
    Intent ActivityHome = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setPaintFlags(tvSignUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        btnSignIn = findViewById(R.id.btnSignIn);
        mAuth = FirebaseAuth.getInstance();
        ActivityHome = new Intent(this, com.fyp.saarthi.ActivityHome.class);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ActivityHome.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivitySignIn.class));

            }
        });

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityRegistration.class));
            }
        });

        btnAdSignIn = (Button) findViewById(R.id.btnAdSignIn);
        btnAdSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityAdminLogin.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),ActivityHome.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"User logged in successfully",Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Could not log in User",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    }


