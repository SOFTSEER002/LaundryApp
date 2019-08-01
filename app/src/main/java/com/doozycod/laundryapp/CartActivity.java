package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomCartAdapter customCartAdapter;
    DBHelper dbHelper;
    List<DBModel> dbModelList;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        dbHelper = new DBHelper(this);
        dbModelList = dbHelper.getDataFromDbForHistory();
        back_btn = findViewById(R.id.back_cart);
        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customCartAdapter = new CustomCartAdapter(this, dbModelList);
        recyclerView.setAdapter(customCartAdapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
