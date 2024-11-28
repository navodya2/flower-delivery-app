package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bloom_room.Domain.FlowerDomain;
import com.example.bloom_room.Helper.ManagementCart;

public class showDetailActivity extends AppCompatActivity {

    private Button addCartBtn;
    private TextView TitleTxt,feeTxt,descriptiontxt,numberOiderTxt;
    private ImageView plusBtn,miniusBtn,picFood,back,cart;
    private FlowerDomain object;
    int numberOrder=1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);


        managementCart=new ManagementCart(this);
        iniView();
        getBundle();

        cart = findViewById(R.id.cartBtn);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextpage = new Intent(getApplicationContext(), CartListActivity.class);
                startActivity(nextpage);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

    }

    private void getBundle() {
        object = (FlowerDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        TitleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptiontxt.setText(object.getDescription());
        numberOiderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    numberOrder = numberOrder + 1;


                numberOiderTxt.setText(String.valueOf(numberOrder));
            }
        });

        miniusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder>1) {
                numberOrder = numberOrder-1;}
                numberOiderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFlower(object);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextpage = new Intent(getApplicationContext(), homepage.class);
                startActivity(nextpage);

                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


    }

    private void iniView() {

        addCartBtn = findViewById(R.id.addToCartBtn);
        TitleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptiontxt = findViewById(R.id.descriptionTxt);
        numberOiderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.pBtn);
        miniusBtn = findViewById(R.id.mBtn);
        picFood = findViewById(R.id.picflower);
        back = findViewById(R.id.backBtn);


    }
}