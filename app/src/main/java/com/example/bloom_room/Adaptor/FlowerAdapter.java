package com.example.bloom_room.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloom_room.R;

import java.util.ArrayList;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerHolder> {
    private Context context;
    private ArrayList flower_id, price_id , description_id,category_id;

    public FlowerAdapter(Context context, ArrayList flower_id, ArrayList price_id, ArrayList description_id, ArrayList category_id) {
        this.context = context;
        this.flower_id = flower_id;
        this.price_id = price_id;
        this.description_id = description_id;
        this.category_id = category_id;
    }

    @NonNull
    @Override
    public FlowerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.flowerentry,parent,false);
        return new FlowerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerHolder holder, int position) {
        holder.flower_id.setText(String.valueOf(flower_id.get(position)));
        holder.price_id.setText(String.valueOf(price_id.get(position)));
        holder.description_id.setText(String.valueOf(description_id.get(position)));

        holder.category_id.setText(String.valueOf(category_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return flower_id.size();
    }

    public class FlowerHolder extends RecyclerView.ViewHolder {
        TextView flower_id,price_id,description_id,category_id;
        public FlowerHolder(@NonNull View itemView) {
            super(itemView);
            flower_id = itemView.findViewById(R.id.textName);
            price_id = itemView.findViewById(R.id.priceTxt);
            description_id = itemView.findViewById(R.id.textDescription);
            category_id = itemView.findViewById(R.id.textcategory);

        }
    }
}
