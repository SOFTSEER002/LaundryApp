package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.laundryapp.Models.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress extends AppCompatActivity {
    DBHelper dbHelper;
    RecyclerView recyclerView;
    SavedAddressAdapter savedAddressAdapter;
    List<AddressModel> addressModels;
    ImageView back_btn;
    TextView add_new_address;
    EditText et_full_name, et_email, et_phone_number, et_house_no, et_address, et_pincode, et_city;
    Button submit_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_saved_address);
        recyclerView = findViewById(R.id.saved_add_recycler);
        addressModels = new ArrayList<>();
        addressModels = dbHelper.getAddressFromDb();
        back_btn = findViewById(R.id.back_cart);
        add_new_address = findViewById(R.id.add_new_address);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < addressModels.size(); i++) {
            Log.e("db Address", "onCreate: " + addressModels.get(i).getFull_name());

        }
        savedAddressAdapter = new SavedAddressAdapter(this, addressModels);
        recyclerView.setAdapter(savedAddressAdapter);
        add_new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAddress();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    void saveAddress() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.save_address_dialog);
        et_full_name = dialog.findViewById(R.id.fullname);
        et_email = dialog.findViewById(R.id.pickupemail);
        et_phone_number = dialog.findViewById(R.id.pickupphone);
        et_city = dialog.findViewById(R.id.city_state);
        et_house_no = dialog.findViewById(R.id.house_no);
        et_address = dialog.findViewById(R.id.address_pickup);
        et_pincode = dialog.findViewById(R.id.pickup_pincode);
        submit_Address = dialog.findViewById(R.id.review_order);
        dialog.show();
        submit_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_full_name.getText().toString().equals("")) {
                    et_full_name.setError("Enter your full name");
                }
                if (et_email.getText().toString().equals("")) {
                    et_email.setError("Enter your email");
                }
                if (et_phone_number.getText().toString().equals("")) {
                    et_phone_number.setError("Enter your phone no");
                }
                if (et_house_no.getText().toString().equals("")) {
                    et_house_no.setError("field can't be blank!");
                }
                if (et_address.getText().toString().equals("")) {
                    et_address.setError("Enter full address");
                }
                if (et_city.getText().toString().equals("")) {
                    et_city.setError("Enter city");
                }
                if (et_pincode.getText().toString().equals("")) {
                    et_pincode.setError("Enter pincode");
                } else {

                    dbHelper.insertAddress(et_full_name.getText().toString(), et_email.getText().toString(), et_phone_number.getText().toString(),
                            et_house_no.getText().toString()
                                    + "," + et_address.getText().toString() + "," + et_city.getText().toString() + "," + et_pincode.getText().toString());
                    addressModels.clear();
                    addressModels = dbHelper.getAddressFromDb();

                    savedAddressAdapter = new SavedAddressAdapter(SavedAddress.this, addressModels);
                    recyclerView.setAdapter(savedAddressAdapter);
                    savedAddressAdapter.notifyDataSetChanged();
                    Toast.makeText(SavedAddress.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }
}
