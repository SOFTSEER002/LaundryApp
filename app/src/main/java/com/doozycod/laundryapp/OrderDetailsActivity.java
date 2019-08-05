package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView tv_top_clothes, tv_jeans_lower, tv_bedsheets, tv_towels, tv_wash_only, tv_iron_only, tv_final_price, tv_time_and_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        tv_top_clothes = findViewById(R.id.order_tshirtsqty);
        tv_jeans_lower = findViewById(R.id.order_jeansqty);
        tv_bedsheets = findViewById(R.id.order_bedsheetsqty);
        tv_towels = findViewById(R.id.order_towelqty);
        tv_wash_only = findViewById(R.id.order_washqty);
        tv_iron_only = findViewById(R.id.order_ironqty);
        tv_time_and_date = findViewById(R.id.order_placed_time);
        tv_final_price = findViewById(R.id.order_cart_price);



    }
}
