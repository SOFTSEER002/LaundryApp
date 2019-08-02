package com.doozycod.laundryapp;

import android.app.Dialog;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SavedAddressAdapter extends RecyclerView.Adapter<SavedAddressAdapter.AddHolder> {
    Context context;
    List<AddressModel> dbList = new ArrayList<>();
    DBHelper dbHelper;

    public SavedAddressAdapter(Context context, List<AddressModel> dbList) {
        this.context = context;
        this.dbList = dbList;
    }

    @NonNull
    @Override
    public SavedAddressAdapter.AddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.saved_address, parent, false);
        dbHelper = new DBHelper(context);
        return new AddHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAddressAdapter.AddHolder holder, final int position) {

        Log.e(TAG, "onBindViewHolder: "+dbList.get(position).getFull_name() );
        holder.saved_name.setText(dbList.get(position).getFull_name());
        holder.saved_address.setText(dbList.get(position).getAddress());
        holder.saved_phone.setText(dbList.get(position).getPhone_no());

        holder.remove_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
    }

    void removeItem(final int position) {
        final Dialog removeDialog = new Dialog(context);
        removeDialog.setContentView(R.layout.delete_dialog);
        removeDialog.show();
        Button remove_item_btn = removeDialog.findViewById(R.id.yes_remove);
        Button cancel_remove_btn = removeDialog.findViewById(R.id.no_remove);
        remove_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteEntry(String.valueOf(dbList.get(position).getUserId()));
                dbList.remove(position);
                notifyDataSetChanged();
                removeDialog.dismiss();


            }
        });
        cancel_remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    class AddHolder extends RecyclerView.ViewHolder {

        TextView saved_name, saved_address, saved_phone;
        ImageView remove_from_cart;

        public AddHolder(@NonNull View itemView) {
            super(itemView);
            saved_name = itemView.findViewById(R.id.saved_name);
            saved_address = itemView.findViewById(R.id.saved_address_db);
            saved_phone = itemView.findViewById(R.id.saved_phone_db);
            remove_from_cart = itemView.findViewById(R.id.remove_address);
        }
    }
}
