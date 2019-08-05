package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.doozycod.laundryapp.Models.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress extends AppCompatActivity {
    DBHelper dbHelper;
    RecyclerView recyclerView;
    SavedAddressAdapter savedAddressAdapter;
    List<AddressModel> addressModels;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_saved_address);
        recyclerView = findViewById(R.id.saved_add_recycler);
        addressModels = new ArrayList<>();
        addressModels = dbHelper.getAddressFromDb();
        back_btn = findViewById(R.id.back_cart);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < addressModels.size(); i++) {
            Log.e("db Address", "onCreate: " + addressModels.get(i).getFull_name());

        }
        savedAddressAdapter = new SavedAddressAdapter(this, addressModels);
        recyclerView.setAdapter(savedAddressAdapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
