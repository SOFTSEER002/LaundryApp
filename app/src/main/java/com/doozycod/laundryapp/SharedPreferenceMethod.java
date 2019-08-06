package com.doozycod.laundryapp;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by ooo on 6/22/2018.
 */

public class SharedPreferenceMethod {
    Context context;
    public static String spemail, sppassword, spfirst_name, splast_name, spMobileNumber, spUser_id, spBook_id, spBook_name, sp_token;

    public SharedPreferenceMethod(Context context) {
        this.context = context;
    }


    public void spInsert(String semail, String spassword, String spfirst_name, String splast_name, String spMobileNumber) {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();


        sp_editior.putString("spUser_id", spUser_id);
        sp_editior.putString("email", semail);
        sp_editior.putString("password", spassword);
        sp_editior.putString("firstname", spfirst_name);
        sp_editior.putString("lastname", splast_name);
        sp_editior.putString("spphone", spMobileNumber);

        sp_editior.commit();
    }


    public boolean checkLogin() {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();

        boolean email = sp.getString("email", "").isEmpty();
        boolean password = sp.getString("password", "").isEmpty();
        return email || password;
    }

    public void saveLogin(boolean login) {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();

        sp_editior.putBoolean("login", login);
        sp_editior.commit();

    }

    public boolean getLogin() {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();
        boolean login = sp.getBoolean("login", false);
        return login;

    }

    public void getUser() {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        spemail = sp.getString("email", "");
        spfirst_name = sp.getString("firstname", "");
        splast_name = sp.getString("lastname", "");
        spMobileNumber = sp.getString("spphone", "");
    }

    public void Logout() {
        SharedPreferences sp = context.getSharedPreferences("laundryAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();
        sp_editior.clear().apply();
    }
}
