package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doozycod.laundryapp.Models.AddressModel;

import java.util.List;

public class OrderPlacedActivity extends AppCompatActivity {
    EditText et_full_name, et_email, et_phone_number, et_house_no, et_address, et_pincode, et_city;
    Button submit_order, change_address;
    DBHelper dbHelper;
    ImageView back_btn;
    List<AddressModel> addressModels;
    LinearLayout pick_details, address_from_db;
    TextView enter_your_picker, db_email, db_fullname, db_address, db_phone;
    boolean isChanged = true;
    Bundle bundle;
    int column_id = 0;

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
        back_btn = findViewById(R.id.back_order_placed);
        pick_details = findViewById(R.id.pickup_de);
        dbHelper = new DBHelper(this);
        enter_your_picker = findViewById(R.id.enter_your_picker);
        db_email = findViewById(R.id.email_db);
        db_fullname = findViewById(R.id.full_name_db);
        db_address = findViewById(R.id.full_address);
        db_phone = findViewById(R.id.phone_db);
        address_from_db = findViewById(R.id.address_from_db);
        change_address = findViewById(R.id.change_address);
        bundle = getIntent().getExtras();
        addressModels = dbHelper.getAddressFromDb();

        Log.e("msg", "onCreate: " + bundle.getInt("coulmn_id"));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        change_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChanged) {
                    pick_details.setVisibility(View.VISIBLE);
                    enter_your_picker.setVisibility(View.VISIBLE);
                    address_from_db.setVisibility(View.GONE);

                    isChanged = false;
                } else {
                    isChanged = true;
                    pick_details.setVisibility(View.GONE);
                    enter_your_picker.setVisibility(View.GONE);
                    address_from_db.setVisibility(View.VISIBLE);
                }
            }
        });
        if (addressModels.size() > 0) {
            pick_details.setVisibility(View.GONE);
            enter_your_picker.setVisibility(View.GONE);
            address_from_db.setVisibility(View.VISIBLE);
            db_fullname.setText(addressModels.get(0).getFull_name());
            db_email.setText(addressModels.get(0).getEmail());
            db_address.setText(addressModels.get(0).getAddress());
            db_phone.setText(addressModels.get(0).getPhone_no());

        } else {
            pick_details.setVisibility(View.VISIBLE);
        }
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dbHelper.getAddressFromDb().size() > 0 && address_from_db.getVisibility() == View.VISIBLE) {

                    if (dbHelper.insertOrder(addressModels.get(0).getEmail(), addressModels.get(0).getPhone_no(), addressModels.get(0).getAddress(), bundle.getInt("coulmn_id"))) {
                        startActivity(new Intent(OrderPlacedActivity.this, MyOrdersActivity.class));

                    }
                } else {
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
                        dbHelper.insertOrder(et_email.getText().toString(), et_phone_number.getText().toString(), et_house_no.getText().toString()
                                + "," + et_address.getText().toString() + "," + et_city.getText().toString() + "," + et_pincode.getText().toString(), bundle.getInt("coulmn_id"));
                        Log.e("check", "onClick: " + " Entered!");
                    }
                }


            }
        });


    }
}
