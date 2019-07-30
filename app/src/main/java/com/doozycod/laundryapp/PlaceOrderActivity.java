package com.doozycod.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        topQty = (TextView) findViewById(R.id.topQty);
        lowerQty = (TextView) findViewById(R.id.lowerQty);
        bedsheetQty = (TextView) findViewById(R.id.bedsheetQty);
        otherQty = (TextView) findViewById(R.id.otherQty);
        topPrice = (TextView) findViewById(R.id.topPrice);
        lowerPrice = (TextView) findViewById(R.id.lowerPrice);
        bedsheetPrice = (TextView) findViewById(R.id.bedsheetPrice);
        otherPrice = (TextView) findViewById(R.id.otherPrice);
        radioService = (RadioGroup) findViewById(R.id.radioService);
        dp = (DatePicker) findViewById(R.id.date_picker);
        tp = (TimePicker) findViewById(R.id.time_picker);
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
                }
                break;
            case R.id.washRB:
                if (checked) {

                    displayTopPrice(topCount * 4);
                    displayLowerPrice(lowerCount * 4);
                    displayBedsheetPrice(bedsheetCount * 10);
                    displayOtherPrice(otherCount * 4);
                }
                break;
            case R.id.bothRB:
                if (checked) {

                    displayTopPrice(topCount * 7);
                    displayLowerPrice(lowerCount * 7);
                    displayBedsheetPrice(bedsheetCount * 12);
                    displayOtherPrice(otherCount * 7);
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

                displayTopPrice(count * 4);
            } else if (clothType == "lower") {
                displayLowerPrice(count * 4);
            } else if (clothType == "bedsheet") {
                displayBedsheetPrice(count * 10);
            } else if (clothType == "other") {
                displayOtherPrice(count * 4);
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

                displayTopPrice(count * 7);
            } else if (clothType == "lower") {
                displayLowerPrice(count * 7);
            } else if (clothType == "bedsheet") {
                displayBedsheetPrice(count * 12);
            } else if (clothType == "other") {
                displayOtherPrice(count * 7);
            }
        }
    }

    private void displayTop(int number) {

        topQty.setText("" + number);
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
