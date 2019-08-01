package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.doozycod.laundryapp.DBHelper.DB_NAME;
import static com.doozycod.laundryapp.DBHelper.SIGNUP_TABLE_NAME;

public class LoginActivity extends AppCompatActivity {
    EditText login_email, login_password;
    Button sign_in;
    TextView sign_up;
    static DBHelper dbHelper;
    SharedPreferenceMethod sharedPreferenceMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);

        login_email = findViewById(R.id.loginemail);
        login_password = findViewById(R.id.loginpass);
        sign_in = findViewById(R.id.signin);
        sign_up = findViewById(R.id.signup_btn);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login_email.getText().toString().equals("") && login_password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "please enter username/password", Toast.LENGTH_SHORT).show();

                } else {
                    if (CheckIsDataAlreadyInDBorNot(SIGNUP_TABLE_NAME, login_email.getText().toString(), login_password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, PriceForLaundry.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "please sign up first", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    public static boolean CheckIsDataAlreadyInDBorNot(String TableName, String emailid, String password) {
        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
        String Query = "Select * from " + TableName + " where email_id = '" + emailid + "' AND password = '" + password + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
