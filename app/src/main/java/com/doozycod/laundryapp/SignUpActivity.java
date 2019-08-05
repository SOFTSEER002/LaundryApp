package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {
    EditText first_name_et, last_name_et, email_et, phone_number_et, password_et, retype_pass_et;
    Button signup_btn;
    TextView sign_in;
    DBHelper dbHelper;
    SharedPreferenceMethod sharedPreferenceMethod;
    CountryCodePicker countryCodePicker;
    String countrycode;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = new DBHelper(this);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        first_name_et = findViewById(R.id.firstname);
        last_name_et = findViewById(R.id.lastname);
        email_et = findViewById(R.id.email);
        phone_number_et = findViewById(R.id.phone);
        password_et = findViewById(R.id.password);
        retype_pass_et = findViewById(R.id.retype_pass);
        sign_in = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.sign_up);
        countryCodePicker = findViewById(R.id.ccp);

        countrycode = countryCodePicker.getSelectedCountryCode();

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countrycode = countryCodePicker.getSelectedCountryCode();

            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first_name_et.getText().toString().equals("")) {
                    first_name_et.setError("please enter first name");

                }
                if (last_name_et.getText().toString().equals("")) {
                    last_name_et.setError("please enter last name");

                }
                if (email_et.getText().toString().equals("")) {
                    email_et.setError("please enter email");
                }
                if (phone_number_et.getText().toString().equals("")) {
                    phone_number_et.setError("please enter phone no");
                }
                if (password_et.getText().toString().equals("")) {
                    password_et.setError("password can't be empty");
                }
                if (retype_pass_et.getText().toString().equals("")) {
                    retype_pass_et.setError("please retype you password");
                } else {
                    if (!password_et.getText().toString().equals(retype_pass_et.getText().toString())) {
                        Toast.makeText(SignUpActivity.this, "password did not matched!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password_et.getText().toString().length() < 6) {
                            Toast.makeText(SignUpActivity.this, "password must have more than 6 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            dbHelper.insertSignup(first_name_et.getText().toString(), last_name_et.getText().toString(), email_et.getText().toString(), countrycode+phone_number_et.getText().toString(), password_et.getText().toString());
                            sharedPreferenceMethod.spInsert(email_et.getText().toString(), password_et.getText().toString(), first_name_et.getText().toString(), last_name_et.getText().toString(), countrycode+phone_number_et.getText().toString());
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                }
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}
