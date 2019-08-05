package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.doozycod.laundryapp.SharedPreferenceMethod.spMobileNumber;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spemail;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spfirst_name;
import static com.doozycod.laundryapp.SharedPreferenceMethod.splast_name;

public class ProfileActivity extends AppCompatActivity {
    TextView usr_name, phone_profile, email_profile, my_Address, myOrders;
    DBHelper dbHelper;
    SharedPreferenceMethod sharedPreferenceMethod;
    ImageView back_btn;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        sharedPreferenceMethod.getUser();
        dbHelper = new DBHelper(this);
        usr_name = findViewById(R.id.usr_name);
        back_btn = findViewById(R.id.back_profile);
        phone_profile = findViewById(R.id.phone_profile);
        email_profile = findViewById(R.id.email_profile);
        my_Address = findViewById(R.id.my_Address);
        myOrders = findViewById(R.id.my_orders_profile);
        usr_name.setText(spfirst_name + " " + splast_name);
        email_profile.setText(spemail);
        phone_profile.setText(spMobileNumber);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        my_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SavedAddress.class));
            }
        });
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MyOrdersActivity.class));
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));
    }
}
