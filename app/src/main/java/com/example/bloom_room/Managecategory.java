package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Managecategory extends AppCompatActivity {

    EditText CategoryName,Categoryid;
    TextView AddCategory,editCategory,DeleteCategory,ViewCategory;
    ImageView back;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managecategory);

        CategoryName = findViewById(R.id.catName);
        Categoryid = findViewById(R.id.catid);
        AddCategory = findViewById(R.id.addCat);
        editCategory = findViewById(R.id.editCat);
        DeleteCategory = findViewById(R.id.delCat);
        ViewCategory = findViewById(R.id.viewCat);
        back = findViewById(R.id.categorybackBtn3);

        DB = new DBHelper(this);

        ViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),categoryList.class));
            }
        });

        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = CategoryName.getText().toString();
                String categoryidTXT = Categoryid.getText().toString();


                if (nameTXT.equals(""))
                    Toast.makeText(Managecategory.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();
                else {

                    Boolean checkCinsertdata = DB.insertCategoryrData(nameTXT,categoryidTXT);

                    if (checkCinsertdata == true)
                        Toast.makeText(Managecategory.this, "Category inserted ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(Managecategory.this, "Entry failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = CategoryName.getText().toString();
                String categoryidTXT = Categoryid.getText().toString();

                if (nameTXT.equals("") && (categoryidTXT.equals("")))
                    Toast.makeText(Managecategory.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();
                else {

                    Boolean check_C_updatedata = DB.updatecategorydata(nameTXT,categoryidTXT);

                    if (check_C_updatedata == true)
                        Toast.makeText(Managecategory.this, "Category Updated ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(Managecategory.this, "Entry failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


        DeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryidTXT = Categoryid.getText().toString();

                if ((categoryidTXT.equals(""))){
                    Toast.makeText(Managecategory.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();}
                else {
                    Boolean delete_C_deletedata = DB.deletecategorydata(categoryidTXT);

                    if (delete_C_deletedata == true)
                        Toast.makeText(Managecategory.this, "Category Deleted ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(Managecategory.this, "Entry failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adminpanel.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }
}