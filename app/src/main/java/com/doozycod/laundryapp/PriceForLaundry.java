package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PriceForLaundry extends AppCompatActivity {
    int imageLaundry[] = {R.drawable.tshirt, R.drawable.jeans, R.drawable.bedsheests, R.drawable.towel};
    String nameLaundry[] = {"Tshirt/Shirts", "Jeans", "Bed Sheets", "Towel"};
    int priceWashLaundry[] = {5, 10, 12, 8};
    int priceIronLaundry[] = {3, 5, 8, 3};
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    Button place_order;
    ImageView go_to_cart_btn, profile_btn;
    boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_for_laundry);
        recyclerView = findViewById(R.id.recycler_view);
        go_to_cart_btn = findViewById(R.id.go_to_cart);
        profile_btn = findViewById(R.id.go_to_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerAdapter = new RecyclerAdapter(this, nameLaundry, imageLaundry, priceWashLaundry, priceIronLaundry);
        recyclerView.setAdapter(recyclerAdapter);
        place_order = findViewById(R.id.placeorder);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PriceForLaundry.this, PlaceOrderActivity.class));
            }
        });
        go_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PriceForLaundry.this, CartActivity.class));
            }
        });
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PriceForLaundry.this, ProfileActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isPressed) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            isPressed = true;
            Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();
        }
    }
}
