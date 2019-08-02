package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderPlacedActivity extends AppCompatActivity {
    EditText et_full_name, et_email, et_phone_number, et_house_no, et_address, et_pincode, et_city;
    Button submit_order;
    DBHelper dbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        et_full_name = findViewById(R.id.fullname);
        et_email = findViewById(R.id.pickupemail);
        et_phone_number = findViewById(R.id.pickupphone);
        et_city = findViewById(R.id.city_state);
        et_house_no = findViewById(R.id.house_no);
        et_address = findViewById(R.id.address_pickup);
        et_pincode = findViewById(R.id.pickup_pincode);
        submit_order = findViewById(R.id.review_order);

        dbHelper = new DBHelper(this);

        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_full_name.getText().toString().equals("")) {
                    et_full_name.setError("Enter your full name");
                }
                if (et_email.getText().toString().equals("")) {
                    et_email.setError("Enter your email");
                }
                if (et_phone_number.getText().toString().equals("")) {
                    et_phone_number.setError("Enter your phone no");
                }
                if (et_house_no.getText().toString().equals("")) {
                    et_house_no.setError("field can't be blank!");
                }
                if (et_address.getText().toString().equals("")) {
                    et_address.setError("Enter full address");
                }
                if (et_city.getText().toString().equals("")) {
                    et_city.setError("Enter city");
                }
                if (et_pincode.getText().toString().equals("")) {
                    et_pincode.setError("Enter pincode");
                } else {
                    dbHelper.insertAddress(et_full_name.getText().toString(), et_email.getText().toString(), et_phone_number.getText().toString(),
                            et_house_no.getText().toString()
                                    + "," + et_address.getText().toString() + "," + et_city.getText().toString() + "," + et_pincode.getText().toString());
                    dbHelper.insertOrder(et_email.getText().toString(), et_phone_number.getText().toString());
                    Log.e("check", "onClick: " + " Entered!");
                }

            }
        });


    }
}
