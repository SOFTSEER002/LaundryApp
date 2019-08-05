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
        holder.tv_top_clothes.setText(String.valueOf(top_clothes));
        holder.tv_jeans_lower.setText(String.valueOf(jeans_lower));
        holder.tv_bedsheets.setText(String.valueOf(bedsheets));
        holder.tv_towels.setText(String.valueOf(towels));
        if (dbModelList.get(position).getDoboth() == 1) {
            holder.tv_wash_only.setText("yes");
            holder.tv_iron_only.setText("yes");
        }

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
        for (int i = 0; i < orderModelList.size(); i++) {
            String query = "select * from " + TABLE_NAME + " where " + "column_id" + " = " + orderModelList.get(i).getColumn_id();
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
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

                } while (cursor.moveToNext());
            }

        }


    }
}
