package com.doozycod.laundryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    Context context;
    String[] laundryNames;
    int priceWash[];
    int priceIron[];
    int imageLaundry[];

    public RecyclerAdapter() {

    }

    public RecyclerAdapter(Context context, String[] laundryNames, int imageLaundry[], int priceWash[], int priceIron[]) {
        this.context = context;
        this.laundryNames = laundryNames;
        this.priceIron = priceIron;
        this.priceWash = priceWash;
        this.imageLaundry = imageLaundry;

    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_grid_view, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {

        Glide.with(context).load(imageLaundry[position]).into(holder.laundryImage);
        holder.laundryname.setText(laundryNames[position]);
        holder.price.setText("\u20B9 " + priceWash[position]);
    }

    @Override
    public int getItemCount() {
        return laundryNames.length;
    }


    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView laundryname, price;
        ImageView laundryImage;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            laundryname = itemView.findViewById(R.id.laundryname);
            laundryImage = itemView.findViewById(R.id.imageview);
            price = itemView.findViewById(R.id.price);
        }
    }
}
