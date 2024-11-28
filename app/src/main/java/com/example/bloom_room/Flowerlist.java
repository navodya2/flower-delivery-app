package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bloom_room.Adaptor.FlowerAdapter;

import java.util.ArrayList;

public class Flowerlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, price,description,category;
    DBHelper DB;
    FlowerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerlist);

        DB = new DBHelper(this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        description = new ArrayList<>();
        category = new ArrayList<>();
        recyclerView = findViewById(R.id.flowerrecyclerview);
        adapter = new FlowerAdapter(this,name,price,description,category);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        display();

    }

    private void display() {
        Cursor cursor = DB.getflowerdata();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));
                price.add(cursor.getString(1));
                category.add(cursor.getString(2));
                description.add(cursor.getString(3));


            }
        }
    }
}