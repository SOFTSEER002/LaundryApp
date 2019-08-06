package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView tv_top_clothes, tv_jeans_lower, tv_bedsheets, tv_towels, tv_wash_only, tv_iron_only, tv_final_price, tv_time_and_date, tv_order_id;
    Bundle getExtras;

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
        tv_order_id = findViewById(R.id.order_id);
        getExtras = getIntent().getExtras();

        tv_order_id.setText("Order No. - " + getExtras.getInt("getId"));
        tv_top_clothes.setText(String.valueOf(getExtras.getInt("getTop_clothes")));
        tv_jeans_lower.setText(String.valueOf(getExtras.getInt("getJeans_lower")));
        tv_bedsheets.setText(String.valueOf(getExtras.getInt("getBedsheets")));
        tv_towels.setText(String.valueOf(getExtras.getInt("getTowels")));
        if (getExtras.getInt("getWash_only") == 1) {
            tv_wash_only.setText("Yes");
            tv_iron_only.setText("No");

        } else if (getExtras.getInt("getIron_only") == 1) {
            tv_wash_only.setText("No");
            tv_iron_only.setText("Yes");

        } else {
            if (getExtras.getInt("getDoboth") == 1) {
                tv_wash_only.setText("Yes");
                tv_iron_only.setText("Yes");
            } else {
                tv_wash_only.setText("No");
                tv_iron_only.setText("No");
            }

        }
        tv_time_and_date.setText(getExtras.getString("getTime") + "hrs  " + getExtras.getString("getDate"));
        tv_final_price.setText("₹ " + String.valueOf(getExtras.getInt("getFinal_price")));
    }
}
