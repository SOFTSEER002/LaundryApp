package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PriceForLaundry extends AppCompatActivity {
    int imageLaundry[] = {R.drawable.tshirt, R.drawable.jeans, R.drawable.bedsheests, R.drawable.towel};
    String nameLaundry[] = {"Tshirt/Shirts", "Jeans", "Bed Sheets", "Towel"};
    int priceWashLaundry[] = {5, 10, 12, 8};
    int priceIronLaundry[] = {3, 5, 8, 3};
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    Button place_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_for_laundry);
        recyclerView = findViewById(R.id.recycler_view);
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
    }
}
