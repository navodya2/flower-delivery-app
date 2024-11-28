package com.example.bloom_room.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloom_room.Domain.FlowerDomain;
import com.example.bloom_room.R;
import com.example.bloom_room.showDetailActivity;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {

    ArrayList<FlowerDomain> popularFlower;

    public PopularAdaptor(ArrayList<FlowerDomain> categoryDomains) {
        this.popularFlower = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(popularFlower.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularFlower.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popularFlower.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), showDetailActivity.class);
                intent.putExtra("object",popularFlower.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFlower.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           title = itemView.findViewById(R.id.ftitle);
            fee = itemView.findViewById(R.id.ffee);
            pic = itemView.findViewById(R.id.fpic);
            addbtn = itemView.findViewById(R.id.addBtn);


        }
    }
}
