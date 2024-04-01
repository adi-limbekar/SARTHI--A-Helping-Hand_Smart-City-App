package com.fyp.saarthi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5500;

    Animation topAnimation, bottomAnimation;
    ImageView appLogo;
    TextView appName, tagLine,team;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animations
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        appLogo = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);
        tagLine = findViewById(R.id.tagLine);
        team = findViewById(R.id.team);

        appLogo.setAnimation(topAnimation);
        appName.setAnimation(bottomAnimation);
        tagLine.setAnimation(bottomAnimation);
        team.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ActivitySplashScreen.this,ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
