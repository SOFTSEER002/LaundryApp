package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    SharedPreferenceMethod sharedPreferenceMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
