package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.doozycod.laundryapp.Models.DBModel;
import com.doozycod.laundryapp.Models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {
    List<DBModel> dbModelList;
    List<OrderModel> orderModelList;
    RecyclerView recyclerView;
    MyOrderAdapter orderAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        dbHelper = new DBHelper(this);
        dbModelList = new ArrayList<>();
        orderModelList = new ArrayList<>();

        dbModelList = dbHelper.getDataFromDbForHistory();
        orderModelList = dbHelper.getOrderFromDb();
        recyclerView = findViewById(R.id.order_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new MyOrderAdapter(this, orderModelList, dbModelList);

        Log.e("orderlist", "orderAct: " + orderModelList.size());
        recyclerView.setAdapter(orderAdapter);

    }
}
