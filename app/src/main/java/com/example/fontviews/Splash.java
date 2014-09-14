package com.example.fontviews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.quick.QuickFontManager;


public class Splash extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // have to use before setcontentview to take effect
        new QuickFontManager.QuickFontBuilder().setDebuggable(true).setCachesize(2).build();
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in =new Intent(Splash.this,MyActivity.class);
                startActivity(in);
                finish();
            }
        },1000);
    }

    @Override
    public void onBackPressed() {
    }
}