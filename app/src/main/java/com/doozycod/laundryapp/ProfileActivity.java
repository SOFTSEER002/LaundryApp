package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import static com.doozycod.laundryapp.DBHelper.SIGNUP_TABLE_NAME;
import static com.doozycod.laundryapp.DBHelper.TABLE_NAME;
import static com.doozycod.laundryapp.DBHelper.USER_ORDER_PLACED;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spMobileNumber;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spUser_id;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spemail;
import static com.doozycod.laundryapp.SharedPreferenceMethod.spfirst_name;
import static com.doozycod.laundryapp.SharedPreferenceMethod.splast_name;

public class ProfileActivity extends AppCompatActivity {
    TextView usr_name, phone_profile, email_profile, my_Address, myOrders, saved_cards;
    DBHelper dbHelper;
    SharedPreferenceMethod sharedPreferenceMethod;
    ImageView back_btn, cart_items, edit_profile;
    Button logout_btn;
    String phone_number;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        sharedPreferenceMethod.getUser();
        dbHelper = new DBHelper(this);
        usr_name = findViewById(R.id.usr_name);
        back_btn = findViewById(R.id.back_profile);
        cart_items = findViewById(R.id.cart_items);
        phone_profile = findViewById(R.id.phone_profile);
        email_profile = findViewById(R.id.email_profile);
        my_Address = findViewById(R.id.my_Address);
        myOrders = findViewById(R.id.my_orders_profile);
        logout_btn = findViewById(R.id.logout_btn);
        saved_cards = findViewById(R.id.saved_cards);
        edit_profile = findViewById(R.id.edit_profile);

        usr_name.setText(spfirst_name + " " + splast_name);
        email_profile.setText(spemail);
        phone_profile.setText(spMobileNumber);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        my_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SavedAddress.class));
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceMethod.saveLogin(false);
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
        cart_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MyOrdersActivity.class));
            }
        });
        saved_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SavedCards.class));
            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_dialog();
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));
    }

    void edit_profile_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.edit_profile_dialog);
        dialog.show();

        final EditText first_name = dialog.findViewById(R.id.firstname_profile);
        final EditText last_name = dialog.findViewById(R.id.lastname_profile);
        final EditText email_profile = dialog.findViewById(R.id.email_profile);
        final EditText phone_profile = dialog.findViewById(R.id.phone_profile);
        ImageView back_profile = dialog.findViewById(R.id.back_profile);
        final CountryCodePicker ccp_profile = dialog.findViewById(R.id.ccp_profile);
        phone_number = ccp_profile.getDefaultCountryCode();
        ccp_profile.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                phone_number = ccp_profile.getSelectedCountryCode();
            }
        });

        Button update_profile = dialog.findViewById(R.id.update_profile);
        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
                SharedPreferences.Editor sp_editior = sp.edit();


                sp_editior.putString("email", email_profile.getText().toString());
                sp_editior.putString("password", sp.getString("password", ""));
                sp_editior.putString("firstname", first_name.getText().toString());
                sp_editior.putString("lastname", last_name.getText().toString());
                sp_editior.putString("spphone", phone_number + phone_profile.getText().toString());
                sp_editior.commit();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("first_name", first_name.getText().toString()); //These Fields should be your String values of actual column names
                cv.put("last_name", last_name.getText().toString());
                cv.put("email_id", email_profile.getText().toString());
                cv.put("phone_number", phone_number + phone_profile.getText().toString());
                db.update(SIGNUP_TABLE_NAME, cv, "email_id= '" + spemail + "'", null);

                usr_name.setText(first_name.getText().toString() + " " + last_name.getText().toString());
                email_profile.setText(email_profile.getText().toString());
                phone_profile.setText(phone_number + phone_profile.getText().toString());
                Toast.makeText(ProfileActivity.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
