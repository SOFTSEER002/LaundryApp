package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    SharedPreferenceMethod sharedPreferenceMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferenceMethod.checkLogin()) {
                    startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, PriceForLaundry.class));
                    finish();
                }

            }
        }, 3000);
        Log.e("Splash", "onCreate: " + sharedPreferenceMethod.checkLogin());
    }
}
