package com.example.grocebud;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class splash extends AppCompatActivity {
    ProgressBar splashProgress;
    private  static  int SPASH_SCREEN_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        splashProgress = findViewById(R.id.splashProgress);
        playProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this,MainActivity.class);

                startActivity(i);
                finish();
            }
        },SPASH_SCREEN_TIME_OUT);



    }
    //Method to run progress bar for 5 seconds
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();
    }
}
