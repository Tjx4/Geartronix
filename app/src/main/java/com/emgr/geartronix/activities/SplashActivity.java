package com.emgr.geartronix.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.emgr.geartronix.R;


public class SplashActivity extends AppCompatActivity {

    ProgressBar startingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_content);
        delaySplashScreen();
        startingProgress = (ProgressBar)findViewById(R.id.prbSplashProgress);
        startingProgress.setProgress(0);
    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void delaySplashScreen() {

        Thread lTimer = new Thread() {

            public void run() {

                try {

                    for (int i = 0; i < 100; i++)
                    {
                        int curProg = i;
                        startingProgress.setProgress(curProg);
                        Thread.sleep(80);
                    }

                    goToLogin();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        lTimer.start();
    }
}