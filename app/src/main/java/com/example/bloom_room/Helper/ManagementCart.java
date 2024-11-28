package com.example.bloom_room.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.bloom_room.Domain.FlowerDomain;
import com.example.bloom_room.Interface.ChangeNumberitemsLitener;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB =new TinyDB(context);
    }

    public void insertFlower(FlowerDomain item){
        ArrayList<FlowerDomain> listFlower=getListCart();
                boolean existAlredy=false;
                int n=0;
                for(int i = 0;i< listFlower.size(); i++){
                    if (listFlower.get(i).getTitle().equals(item.getTitle())){
                        existAlredy=true;
                        n=i;
                        break;
                    }
                }

        if (existAlredy) {
            listFlower.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFlower.add(item);
        }
        tinyDB.putListObject("CartList",listFlower);
        Toast.makeText(context, "Added to your card", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FlowerDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFlower(ArrayList<FlowerDomain>listFlower, int position, ChangeNumberitemsLitener changeNumberitemsLitener){
        listFlower.get(position).setNumberInCart(listFlower.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listFlower);
        changeNumberitemsLitener.changed();
    }

    public void minusNumberFlower(ArrayList<FlowerDomain>listFlower, int position, ChangeNumberitemsLitener changeNumberitemsLitener){
        if (listFlower.get(position).getNumberInCart()==1){
            listFlower.remove(position);

        }else {
            listFlower.get(position).setNumberInCart(listFlower.get(position).getNumberInCart()-1);

        }
        tinyDB.putListObject("CartList",listFlower);
        changeNumberitemsLitener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FlowerDomain> listflower=getListCart();
        double fee=0;
        for (int i=0; i<listflower.size(); i++){
            fee = fee + (listflower.get(i).getFee() * listflower.get(i).getNumberInCart());
        }
        return fee;

    }
}
