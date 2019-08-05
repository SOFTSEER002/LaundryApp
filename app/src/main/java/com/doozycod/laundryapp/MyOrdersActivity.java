package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.doozycod.laundryapp.Models.DBModel;
import com.doozycod.laundryapp.Models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {
    List<DBModel> dbModelList;
    List<OrderModel> orderModelList;
    RecyclerView recyclerView;
    MyOrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        dbModelList = new ArrayList<>();
        orderModelList = new ArrayList<>();
        recyclerView = findViewById(R.id.order_recycler_view);
        orderAdapter = new MyOrderAdapter(this, orderModelList, dbModelList);

    }
}
