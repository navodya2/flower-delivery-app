package com.example.bloom_room.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloom_room.Domain.FlowerDomain;
import com.example.bloom_room.Helper.ManagementCart;
import com.example.bloom_room.Interface.ChangeNumberitemsLitener;
import com.example.bloom_room.R;

import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {

    private ArrayList<FlowerDomain> flowerDomains;
    private ManagementCart managementCart;
    private ChangeNumberitemsLitener changeNumberListener;

    public CartListAdaptor(ArrayList<FlowerDomain> flowerDomains, Context context, ChangeNumberitemsLitener changeNumberListener) {
        this.flowerDomains = flowerDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberListener = changeNumberListener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(flowerDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(flowerDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((flowerDomains.get(position).getNumberInCart() * flowerDomains.get(position).getFee()) * 100) / 100 ));
        holder.num.setText(String.valueOf(flowerDomains.get(position).getNumberInCart()));

        int drawbleResouseId = holder.itemView.getContext().getResources().getIdentifier(flowerDomains.get(position).getPic()
                ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawbleResouseId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFlower(flowerDomains, position, new ChangeNumberitemsLitener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFlower(flowerDomains,position, new ChangeNumberitemsLitener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return flowerDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachitem);
            pic=itemView.findViewById(R.id.picCart);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            totalEachItem=itemView.findViewById(R.id.totalEachitem);
            num=itemView.findViewById(R.id.numberItemTxt);

        }
    }
}
