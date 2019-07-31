package com.doozycod.laundryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "laundry.db";
    private static final String TABLE_NAME = "laundryOrdersTable";
    private static final String IRONANDWASH = "doboth";
    private static final String WASH_ONLY = "wash_only";
    private static final String IRON_ONLY = "iron_only";
    private static final String TOP_CLOTHES = "top_clothes";
    private static final String JEANS_LOWER = "jeans_lower";
    private static final String BEDSHEETS = "bedsheets";
    private static final String OTHERS = "towels";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(column_id integer primary key,top_clothes integer,jeans_lower integer,bedsheets integer," +
                "towels integer,wash_only integer,iron_only integer,doboth integer, final_price integer,date text,time text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //    Creating a method to get product from history_prduct table
    public List<DBModel> getDataFromDbForHistory() {

        // Declaring a List object
        List<DBModel> modelList = new ArrayList<>();

//        String for query
        String query = "select * from " + TABLE_NAME;


        SQLiteDatabase db = this.getWritableDatabase();

//        creating cursor object to use query to get the data from table
        Cursor cursor = db.rawQuery(query, null);

//        Condition for set the data in pojo class
        if (cursor.moveToFirst()) {
            do {
//                creating DatabaseModel object to set the list using by Pojo
                DBModel model = new DBModel();
                model.setTop_clothes((cursor).getInt(1));
                model.setJeans_lower((cursor).getInt(2));
                model.setBedsheets((cursor).getInt(3));
                model.setTowels((cursor).getInt(4));
                model.setWash_only((cursor).getInt(5));
                model.setIron_only((cursor).getInt(6));
                model.setDoboth((cursor).getInt(7));
                model.setFinal_price((cursor).getInt(8));
                model.setDate((cursor).getString(9));
                model.setTime((cursor).getString(10));
                modelList.add(model);
            } while (cursor.moveToNext());
        }


        Log.d("Cart data", modelList.toString());


        return modelList;
    }

    public boolean insert(int top_clothes, int jeans_lower, int bedsheets, int towels, int wash_only, int iron_only, int doboth, int final_price, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("top_clothes", top_clothes);
        contentValues.put("jeans_lower", jeans_lower);
        contentValues.put("bedsheets", bedsheets);
        contentValues.put("towels", towels);
        contentValues.put("wash_only", wash_only);
        contentValues.put("iron_only", iron_only);
        contentValues.put("doboth", doboth);
        contentValues.put("final_price", final_price);
        contentValues.put("date", date);
        contentValues.put("time", time);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

}
