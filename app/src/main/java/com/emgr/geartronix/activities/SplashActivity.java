package com.emgr.geartronix.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.emgr.geartronix.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delaySplashScreen();
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
                    Thread.sleep(3000);
                    goToLogin();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        lTimer.start();
    }
}