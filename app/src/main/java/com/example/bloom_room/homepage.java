package com.example.bloom_room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bloom_room.Adaptor.CategoryAdaptor;
import com.example.bloom_room.Adaptor.PopularAdaptor;
import com.example.bloom_room.Domain.CategoryDomain;
import com.example.bloom_room.Domain.FlowerDomain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

TextView flowerviewall, categoryviewall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recycleviweCategory();
        recyclerViewPopular();
        bottomNavigation();

        flowerviewall =findViewById(R.id.flowersViewAll);
        categoryviewall = findViewById(R.id.categoryViewAll);

        flowerviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Flowerlist.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        categoryviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), categoryList.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME_KEY")) {
            String username = intent.getStringExtra("USERNAME_KEY");

            // Find the TextView
            TextView userText = findViewById(R.id.userText);
            // Set the username to the TextView
            userText.setText(username);
        }

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
                    startActivity(new Intent(getApplicationContext(), Flowerlist.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_notifications) {
                    startActivity(new Intent(getApplicationContext(), CartListActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    private void recycleviweCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerview);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category= new ArrayList<>();
        category.add(new CategoryDomain("Wedding","cat_1"));
        category.add(new CategoryDomain("Bouquet","cat_2"));
        category.add(new CategoryDomain("Branch","cat_3"));
        category.add(new CategoryDomain("Houseplant","cat_4"));
        category.add(new CategoryDomain("Box","cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerview2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FlowerDomain> flowerlist=new ArrayList<>();
        flowerlist.add(new FlowerDomain("Royal Lily","flower_3","Timeless grace, pure white petals, sweet fragrance. Perfect gift for love, weddings, and solace",9.78));

        flowerlist.add(new FlowerDomain("Red Rose","flower_2"," Romantic allure, velvety petals, tender scent. Ideal for love declarations and anniversaries",100));
        flowerlist.add(new FlowerDomain("Tulip","flower_1","Vibrant yellow blooms, herald of spring. Brings joy to any occasion and symbolizes new beginnings ",500));
        flowerlist.add(new FlowerDomain("Bouquet Roses","flower_8"," Exotic allure, elegant blooms, refined beauty. An exquisite gift for sophistication and admiration",1000));
        flowerlist.add(new FlowerDomain("Bourbon Rose","flower_4","Bright and bold, radiates happiness. Perfect for lifting spirits and expressing gratitude",900));
        flowerlist.add(new FlowerDomain("Bunches","flower_7"," Delicate blue blossoms, woodland enchantment. Evokes nostalgia and serenity in wildflower arrangements.",890));
        flowerlist.add(new FlowerDomain("Flower Box","flower_5"," The flowers inside the box may range from roses, lilies, tulips, daisies, orchids, or any other popular or seasonal flowers.",890));

        adapter2 = new PopularAdaptor(flowerlist);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}

