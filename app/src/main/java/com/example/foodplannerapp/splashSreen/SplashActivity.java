package com.example.foodplannerapp.splashSreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.startSreen.StartActivity;

public class SplashActivity extends AppCompatActivity {
    private final int  time = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, time);
    }
}