package com.doozycod.laundryapp;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doozycod.laundryapp.Models.AddressModel;
import com.doozycod.laundryapp.Models.DBModel;
import com.doozycod.laundryapp.Models.OrderModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.doozycod.laundryapp.DBHelper.TABLE_NAME;
import static com.doozycod.laundryapp.DBHelper.USER_ORDER;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.AddHolder> {
    Context context;
    List<OrderModel> orderModelList = new ArrayList<>();
    List<DBModel> dbModelList = new ArrayList<>();
    List<DBModel> dbList = new ArrayList<>();
    DBHelper dbHelper;
    int top_clothes;
    int jeans_lower;
    int bedsheets;
    int towels;
    int wash_only;
    int iron_only;
    int doboth;
    int final_price;
    String date, time;

    public MyOrderAdapter(Context context, List<OrderModel> dbList, List<DBModel> dbModelList) {
        this.context = context;
        this.orderModelList = dbList;
        this.dbModelList = dbModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.AddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_list_view, parent, false);
        dbHelper = new DBHelper(context);

        return new AddHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.AddHolder holder, final int position) {
        getOrder();
        holder.tv_top_clothes.setText(String.valueOf(dbList.get(position).getTop_clothes()));
        holder.tv_jeans_lower.setText(String.valueOf(dbList.get(position).getJeans_lower()));
        holder.tv_bedsheets.setText(String.valueOf(dbList.get(position).getBedsheets()));
        holder.tv_towels.setText(String.valueOf(dbList.get(position).getTowels()));
        if (dbList.get(position).getWash_only() == 1) {
            holder.tv_wash_only.setText("Yes");
            holder.tv_iron_only.setText("No");

        } else if (dbList.get(position).getIron_only() == 1) {
            holder.tv_wash_only.setText("No");
            holder.tv_iron_only.setText("Yes");

        } else {
            if (dbList.get(position).getDoboth() == 1) {
                holder.tv_wash_only.setText("Yes");
                holder.tv_iron_only.setText("Yes");
            } else {
                holder.tv_wash_only.setText("No");
                holder.tv_iron_only.setText("No");
            }
        }
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_NAME); //delete all rows in a table
//        db.close();
    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    class AddHolder extends RecyclerView.ViewHolder {

        TextView tv_top_clothes, tv_jeans_lower, tv_bedsheets, tv_towels, tv_wash_only, tv_iron_only;

        public AddHolder(@NonNull View itemView) {
            super(itemView);
            tv_top_clothes = itemView.findViewById(R.id.tshirtsqty_ad);
            tv_jeans_lower = itemView.findViewById(R.id.jeansqty_ad);
            tv_bedsheets = itemView.findViewById(R.id.bedsheetsqty_ad);
            tv_towels = itemView.findViewById(R.id.towelqty_ad);
            tv_wash_only = itemView.findViewById(R.id.washqty_ad);
            tv_iron_only = itemView.findViewById(R.id.ironqty_ad);
        }
    }

    void getOrder() {

            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            for (int i1 = 0; i1 < orderModelList.size(); i1++) {
                String query = "select * from " + TABLE_NAME + " where " + "column_id" + " = " + orderModelList.get(i1).getColumn_id();
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    do {
                        DBModel model = new DBModel();


                        top_clothes = cursor.getInt(cursor.getColumnIndex("top_clothes"));
                        jeans_lower = cursor.getInt(cursor.getColumnIndex("jeans_lower"));
                        bedsheets = cursor.getInt(cursor.getColumnIndex("bedsheets"));
                        towels = cursor.getInt(cursor.getColumnIndex("towels"));
                        wash_only = cursor.getInt(cursor.getColumnIndex("wash_only"));
                        iron_only = cursor.getInt(cursor.getColumnIndex("iron_only"));
                        doboth = cursor.getInt(cursor.getColumnIndex("doboth"));
                        final_price = cursor.getInt(cursor.getColumnIndex("final_price"));
                        date = cursor.getString(cursor.getColumnIndex("date"));
                        time = cursor.getString(cursor.getColumnIndex("time"));


                        model.setTop_clothes(top_clothes);
                        model.setJeans_lower(jeans_lower);
                        model.setBedsheets(bedsheets);
                        model.setTowels(towels);
                        model.setWash_only(wash_only);
                        model.setIron_only(iron_only);
                        model.setDoboth(doboth);
                        model.setFinal_price(final_price);
                        model.setDate(date);
                        model.setTime(time);
                        dbList.add(model);
                    } while (cursor.moveToNext());

            }
        }



    }
}
