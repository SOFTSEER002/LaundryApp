package com.doozycod.laundryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {
    int topCount = 0;
    int lowerCount = 0;
    int bedsheetCount = 0;
    int otherCount = 0;
    public RadioGroup radioService;
    public RadioButton radiobtn;
    DatePicker dp;
    TextView topQty, lowerQty, bedsheetQty, otherQty, topPrice, lowerPrice, bedsheetPrice, otherPrice;
    TimePicker tp;
    DBHelper dbHelper;
    Button add_to_bucket;
    int wash = 0;
    int iron = 0;
    int do_both = 0;
    int cart_total = 0;
    int month, date, year, hours, minutes;
    List<DBModel> dbModelList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
//        db
        dbHelper = new DBHelper(this);

        topQty = findViewById(R.id.topQty);
        lowerQty = findViewById(R.id.lowerQty);
        bedsheetQty = findViewById(R.id.bedsheetQty);
        otherQty = findViewById(R.id.otherQty);
        topPrice = findViewById(R.id.topPrice);
        lowerPrice = findViewById(R.id.lowerPrice);
        bedsheetPrice = findViewById(R.id.bedsheetPrice);
        otherPrice = findViewById(R.id.otherPrice);
        radioService = findViewById(R.id.radioService);
        dp = findViewById(R.id.date_picker);
        tp = findViewById(R.id.time_picker);
        add_to_bucket = findViewById(R.id.add_to_bucket);
        dbModelList = dbHelper.getDataFromDbForHistory();
        for (int i = 0; i < dbModelList.size(); i++) {
            Log.e("msg", "onCreate: " + dbModelList.get(i).getFinal_price() + "\n" + dbModelList.get(i).getTop_clothes() + "\n" + dbModelList.get(i).getBedsheets());
        }
        dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int Year, int Month, int Date) {
                Log.e("msg", "onDateChanged: " + Year + "\n" + Month + "\n" + Date);
                year = Year;
                month = Month;
                date = Date;
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int mints) {
                Log.e("msg", "onTimeChanged: " + hour + "\n" + mints);
                hours = hour;
                minutes = mints;
            }
        });

        add_to_bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int top = Integer.parseInt(topPrice.getText().toString());
                int lower = Integer.parseInt(lowerPrice.getText().toString());
                int bed_sheets = Integer.parseInt(bedsheetPrice.getText().toString());
                int others = Integer.parseInt(otherPrice.getText().toString());
                String day = date + "/" + month + "/" + year;
                String time = hours + ":" + minutes;
                Log.e("msg", "onClick: " + top + "\n" + lower + "\n" + bed_sheets + "\n" + others + "\n" + day + "\n" + time);
                cart_total = top + lower + bed_sheets + others;
                dbHelper.insert(top, lower, bed_sheets, others,
                        wash, iron, do_both, cart_total, day, time);

                startActivity(new Intent(PlaceOrderActivity.this, CartActivity.class));

            }
        });

    }

    public void clickradioButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.ironRB:
                if (checked) {
                    displayTopPrice(topCount * 3);
                    displayLowerPrice(lowerCount * 3);
                    displayBedsheetPrice(bedsheetCount * 3);
                    displayOtherPrice(otherCount * 3);
                    iron = 1;
                    wash = 0;
                    do_both = 0;
                }
                break;
            case R.id.washRB:
                if (checked) {

                    displayTopPrice(topCount * 5);
                    displayLowerPrice(lowerCount * 10);
                    displayBedsheetPrice(bedsheetCount * 12);
                    displayOtherPrice(otherCount * 8);
                    iron = 0;
                    wash = 1;
                    do_both = 0;
                }
                break;
            case R.id.bothRB:
                if (checked) {

                    displayTopPrice(topCount * 8);
                    displayLowerPrice(lowerCount * 13);
                    displayBedsheetPrice(bedsheetCount * 15);
                    displayOtherPrice(otherCount * 11);
                    iron = 0;
                    wash = 0;
                    do_both = 1;
                }
                break;
        }
    }

    public void updatePrice(String clothType, int count) {
        int selectedId = radioService.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radiobtn = (RadioButton) findViewById(selectedId);
        if (radiobtn.getText().equals("Wash")) {
            if (clothType == "top") {

                displayTopPrice(count * 5);
            } else if (clothType == "lower") {
                displayLowerPrice(count * 10);
            } else if (clothType == "bedsheet") {
                displayBedsheetPrice(count * 12);
            } else if (clothType == "other") {
                displayOtherPrice(count * 8);
            }
        } else if (radiobtn.getText().equals("Iron")) {
            if (clothType == "top") {

                displayTopPrice(count * 3);
            } else if (clothType == "lower") {
                displayLowerPrice(count * 3);
            } else if (clothType == "bedsheet") {
                displayBedsheetPrice(count * 3);
            } else if (clothType == "other") {
                displayOtherPrice(count * 3);
            }
        } else if (radiobtn.getText().equals("Wash and Iron")) {

            if (clothType == "top") {

                displayTopPrice(count * 8);
            } else if (clothType == "lower") {
                displayLowerPrice(count * 13);
            } else if (clothType == "bedsheet") {
                displayBedsheetPrice(count * 15);
            } else if (clothType == "other") {
                displayOtherPrice(count * 11);
            }
        }
    }

    private void displayTop(int number) {

        topQty.setText("" + number);
    }

    public void topInc(View view) {
        if (topCount == 12) {
            topCount = 12;
            Toast.makeText(getApplicationContext(), "Can't be more than 12", Toast.LENGTH_SHORT).show();
        } else {
            topCount = topCount + 1;
            displayTop(topCount);

        }
        updatePrice("top", topCount);
    }

    public void topDec(View view) {
        if (topCount == 0) {
            topCount = 0;
            Toast.makeText(getApplicationContext(), "Can't be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            topCount = topCount - 1;
            displayTop(topCount);
        }
        updatePrice("top", topCount);
    }

    public void lowerInc(View view) {
        if (lowerCount == 12) {
            lowerCount = 12;
            Toast.makeText(getApplicationContext(), "Can't be more than 12", Toast.LENGTH_SHORT).show();
        } else {
            lowerCount = lowerCount + 1;
            displayLower(lowerCount);
        }
        updatePrice("lower", lowerCount);
    }

    public void lowerDec(View view) {
        if (lowerCount == 0) {
            lowerCount = 0;
            Toast.makeText(getApplicationContext(), "Can't be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            lowerCount = lowerCount - 1;
            displayLower(lowerCount);
        }
        updatePrice("lower", lowerCount);
    }


    public void bedsheetInc(View view) {
        if (bedsheetCount == 12) {
            bedsheetCount = 12;
            Toast.makeText(getApplicationContext(), "Can't be more than 12", Toast.LENGTH_SHORT).show();
        } else {
            bedsheetCount = bedsheetCount + 1;
            displayBedsheet(bedsheetCount);
        }
        updatePrice("bedsheet", bedsheetCount);
    }

    public void bedsheetDec(View view) {
        if (bedsheetCount == 0) {
            bedsheetCount = 0;
            Toast.makeText(getApplicationContext(), "Can't be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            bedsheetCount = bedsheetCount - 1;
            displayBedsheet(bedsheetCount);
        }
        updatePrice("bedsheet", bedsheetCount);
    }

    public void otherInc(View view) {
        if (otherCount == 12) {
            otherCount = 12;
            Toast.makeText(getApplicationContext(), "Can't be more than 12", Toast.LENGTH_SHORT).show();
        } else {
            otherCount = otherCount + 1;
            displayOther(otherCount);
        }
        updatePrice("other", otherCount);
    }

    public void otherDec(View view) {
        if (otherCount == 0) {
            otherCount = 0;
            Toast.makeText(getApplicationContext(), "Can't be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            otherCount = otherCount - 1;
            displayOther(otherCount);
        }
        updatePrice("other", otherCount);
    }

    private void displayLower(int number) {
        lowerQty.setText("" + number);
    }

    private void displayBedsheet(int number) {
        bedsheetQty.setText("" + number);
    }

    private void displayOther(int number) {
        otherQty.setText("" + number);
    }

    private void displayTopPrice(int number) {
        topPrice.setText("" + number);
    }

    private void displayLowerPrice(int number) {

        lowerPrice.setText("" + number);
    }

    private void displayBedsheetPrice(int number) {

        bedsheetPrice.setText("" + number);
    }

    private void displayOtherPrice(int number) {

        otherPrice.setText("" + number);
    }
}
