package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bloom_room.Adaptor.CateGoryAdapter;

import java.util.ArrayList;

public class categoryList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name;
    DBHelper DB;
    CateGoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        recyclerView = findViewById(R.id.categoryrecyclerview);
        adapter = new CateGoryAdapter(this,name);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        display();

    }

    private void display() {
        Cursor cursor = DB.getcategorydata();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));


            }
        }
    }
}