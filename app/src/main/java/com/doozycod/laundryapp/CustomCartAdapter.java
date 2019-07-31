package com.doozycod.laundryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomCartAdapter extends RecyclerView.Adapter<CustomCartAdapter.CartHolder> {
    Context context;
    List<DBModel> dbList = new ArrayList<>();

    public CustomCartAdapter(Context context, List<DBModel> dbList) {
        this.context = context;
        this.dbList = dbList;
    }

    @NonNull
    @Override
    public CustomCartAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_listview, parent, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCartAdapter.CartHolder holder, int position) {


        holder.TOPclothes.setText(dbList.get(position).getTop_clothes());
        holder.JeansLower.setText(dbList.get(position).getJeans_lower());
        holder.Bedsheets.setText(dbList.get(position).getBedsheets());
        holder.Towels.setText(dbList.get(position).getTowels());
        holder.wash.setText(dbList.get(position).getWash_only());
        holder.iron.setText(dbList.get(position).getIron_only());
        holder.cart_price.setText(dbList.get(position).getFinal_price());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {

        TextView cart_price, TOPclothes, JeansLower, Bedsheets, Towels, wash, iron;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            cart_price = itemView.findViewById(R.id.cart_price);
            TOPclothes = itemView.findViewById(R.id.tshirtsqty);
            JeansLower = itemView.findViewById(R.id.jeansqty);
            Bedsheets = itemView.findViewById(R.id.bedsheetsqty);
            Towels = itemView.findViewById(R.id.towelqty);
            wash = itemView.findViewById(R.id.washqty);
            iron = itemView.findViewById(R.id.ironqty);
        }
    }
}
