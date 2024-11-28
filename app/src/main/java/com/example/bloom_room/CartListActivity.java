package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.bloom_room.Adaptor.CartListAdaptor;
import com.example.bloom_room.Helper.ManagementCart;
import com.example.bloom_room.Interface.ChangeNumberitemsLitener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import android.os.Handler;



public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;
TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt,checkout;

ImageView back;
private double tax;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        iniList();
        CalculateCart();
        bottomNavigation();





    }

    private void bottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    startActivity(new Intent(getApplicationContext(), homepage.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_dashboard) {
                    startActivity(new Intent(getApplicationContext(), showDetailActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_notifications) {
                    startActivity(new Intent(getApplicationContext(), CartListActivity.class));
                    return true;
                }
                return false;
            }
        });
    }


    private void initView() {
        recyclerViewList= findViewById(R.id.recyclerview);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.DeliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.cartView);
        checkout =findViewById(R.id.checkoutBtn);
        back = findViewById(R.id.checkoutBack);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test1 = new Intent(getApplicationContext(), homepage.class);
                startActivity(test1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void iniList(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setVisibility(View.GONE); // Initially set to GONE
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter= new CartListAdaptor(managementCart.getListCart(), this, new ChangeNumberitemsLitener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                emptyTxt.setVisibility(View.GONE);
            }
        });
    }

    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal=Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+delivery);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

}