package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.doozycod.laundryapp.Models.DBModel;
import com.doozycod.laundryapp.Models.OrderModel;
import com.doozycod.laundryapp.Models.UserOrderModel;

import java.util.ArrayList;
import java.util.List;

import static com.doozycod.laundryapp.DBHelper.TABLE_NAME;

public class MyOrdersActivity extends AppCompatActivity {
    List<DBModel> dbModelList;
    List<OrderModel> orderModelList;
    List<UserOrderModel> userOrderModels;
    RecyclerView recyclerView;
    MyOrderAdapter orderAdapter;
    DBHelper dbHelper;
    ImageView back_btn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, PriceForLaundry.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        dbHelper = new DBHelper(this);
        dbModelList = new ArrayList<>();
        orderModelList = new ArrayList<>();
        back_btn = findViewById(R.id.back_my_order);
        dbModelList = dbHelper.getDataFromDbForHistory();
        orderModelList = dbHelper.getOrderFromDb();
        userOrderModels = dbHelper.getUserOrder();

        recyclerView = findViewById(R.id.order_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new MyOrderAdapter(this, orderModelList, dbModelList, userOrderModels);

        Log.e("orderlist", "orderAct: " + orderModelList.size());
        recyclerView.setAdapter(orderAdapter);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME); //delete all rows in a table
        db.close();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
