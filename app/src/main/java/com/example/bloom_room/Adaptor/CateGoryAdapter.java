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
public class CateGoryAdapter extends RecyclerView.Adapter<CateGoryAdapter.categoryholder> {
    private Context context;
    private ArrayList category_id;
    public CateGoryAdapter(Context context, ArrayList category_id) {
        this.context = context;
        this.category_id = category_id;
    }
    @NonNull
    @Override
    public categoryholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.categoryentry,parent,false);
        return new categoryholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull categoryholder holder, int position) {
        holder.category_id.setText(String.valueOf(category_id.get(position)));
    }
    @Override
    public int getItemCount() {
        return category_id.size();
    }
    public class categoryholder extends RecyclerView.ViewHolder {
        TextView category_id;
        public categoryholder(@NonNull View itemView) {
            super(itemView);
            category_id = itemView.findViewById(R.id.textcategory);
        }
    }
}
