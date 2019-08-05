package com.doozycod.laundryapp.Models;

public class OrderModel {
    String order_email, phone_number, address;
        int column_id;

    public int getColumn_id() {
        return column_id;
    }

    public void setColumn_id(int coluumn_id) {
        this.column_id = coluumn_id;
    }

    public String getOrder_email() {
        return order_email;
    }

    public void setOrder_email(String order_email) {
        this.order_email = order_email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
