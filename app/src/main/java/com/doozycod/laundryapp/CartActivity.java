package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomCartAdapter customCartAdapter;
    DBHelper dbHelper;
    List<DBModel> dbModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        dbHelper = new DBHelper(this);
        dbModelList = dbHelper.getDataFromDbForHistory();

        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customCartAdapter = new CustomCartAdapter(this, dbModelList);
        recyclerView.setAdapter(customCartAdapter);

    }
}
