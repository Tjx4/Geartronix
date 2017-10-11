package com.emgr.geartronix;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Context context;
    private Button btnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        ActionBar ab = basicActionBarConfiguration(getString(R.string.app_name));
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        /*ab.setTitle(getResources().getString(R.string.app_name));
        ab.setDisplayHomeAsUpEnabled(true);*/

        btnOne = (Button)findViewById(R.id.Button1);
        btnOne.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                goToGallery(view);
            }
        });
    }

    public void goToGallery(View view) {
        Intent i = new Intent(context, GalleryActivity.class);
        startActivity(i);
    }

    protected ActionBar basicActionBarConfiguration(String title) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);

        return ab;
    }
}
