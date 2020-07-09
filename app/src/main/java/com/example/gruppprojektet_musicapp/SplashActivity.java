package com.example.gruppprojektet_musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash screen

        EasySplashScreen config = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withAfterLogoText("My Music Event Manager")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        getSupportActionBar().hide();
        View splashScreen = config.create();
        setContentView(splashScreen);
    }
}
