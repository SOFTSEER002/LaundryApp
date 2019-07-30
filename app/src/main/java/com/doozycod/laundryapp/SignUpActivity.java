package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    EditText first_name_et, last_name_et, email_et, phone_number_et, password_et, retype_pass_et;
    Button signup_btn;
    TextView sign_in;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        first_name_et = findViewById(R.id.firstname);
        last_name_et = findViewById(R.id.lastname);
        email_et = findViewById(R.id.email);
        phone_number_et = findViewById(R.id.phone);
        password_et = findViewById(R.id.password);
        retype_pass_et = findViewById(R.id.retype_pass);
        sign_in = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.sign_up);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }
}
