package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doozycod.laundryapp.Models.DBModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements OnItemClick {
    RecyclerView recyclerView;
    CustomCartAdapter customCartAdapter;
    DBHelper dbHelper;
    List<DBModel> dbModelList;
    ImageView back_btn;
    int cart_total = 0;
    TextView cart_total_price, cartEmpty;
    RelativeLayout cart_value;
    Button checkout_page;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        dbHelper = new DBHelper(this);
        dbModelList = dbHelper.getDataFromDbForHistory();
        back_btn = findViewById(R.id.back_cart);
        cart_value = findViewById(R.id.cartsss);
        cart_total_price = findViewById(R.id.cart_total_tv);
        cartEmpty = findViewById(R.id.cart_empty);
        checkout_page = findViewById(R.id.checkout_page);
        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkout_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, OrderPlacedActivity.class));
            }
        });
        customCartAdapter = new CustomCartAdapter(this, dbModelList, this);
        recyclerView.setAdapter(customCartAdapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        for (int i = 0; i < dbModelList.size(); i++) {

            cart_total = cart_total + dbModelList.get(i).getFinal_price();
        }

        cart_total_price.setText("\u20B9 " + cart_total);

        Log.e("msg", "onCreate: \u20B9 " + cart_total);

    }

    @Override
    public void onClick(List<DBModel> list) {
        cart_total_price.setText("");
        cart_total = 0;
        if (list.size() == 0) {
            cart_value.setVisibility(View.GONE);
            cartEmpty.setVisibility(View.VISIBLE);
        } else {

            cart_value.setVisibility(View.VISIBLE);
            cartEmpty.setVisibility(View.GONE);
            for (int i = 0; i < list.size(); i++) {
                cart_total = cart_total + list.get(i).getFinal_price();
            }
            cart_total_price.setText("\u20B9 " + cart_total);
        }

//        for (int i = 0; i < list.size(); i++) {
//            cart_total = cart_total + list.get(i).getFinal_price();
//        }
//        cart_total_price.setText("\u20B9 " + cart_total);

    }
}
