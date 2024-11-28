package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageFlowers extends AppCompatActivity {

    EditText FlowerName, FlowerPrice,FlowerDescription,Flowerid,flowercategory;
    TextView AddF,UpdateF,DeleteF,ViewF;
    DBHelper DB;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flowers);

        Flowerid = findViewById(R.id.fid);
        FlowerName = findViewById(R.id.Fname);
        FlowerPrice = findViewById(R.id.Fprice);
        FlowerDescription = findViewById(R.id.Fdescription);
        AddF = findViewById(R.id.addFlowerBtn);
        UpdateF = findViewById(R.id.updateFlowerBtn);
        DeleteF = findViewById(R.id.deleteFlowerBtn);
        ViewF = findViewById(R.id.viewFlowersBtn);
        flowercategory = findViewById(R.id.fcategory);
        back = findViewById(R.id.flowerbackBtn);

        DB = new DBHelper(this);


        ViewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),Flowerlist.class));
            }
        });

        AddF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = FlowerName.getText().toString();
                String priceTXT = FlowerPrice.getText().toString();
                String descriptionTXT = FlowerDescription.getText().toString();
                String idTXT = Flowerid.getText().toString();
                String categoryTXT = flowercategory.getText().toString();

                if (nameTXT.equals("") && priceTXT.equals("") && descriptionTXT.equals("") && idTXT.equals("") && categoryTXT.equals(""))
                    Toast.makeText(ManageFlowers.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();
                else if (nameTXT.equals(""))
                    Toast.makeText(ManageFlowers.this, "Please enter the Flower name", Toast.LENGTH_SHORT).show();
                else if (priceTXT.equals(""))
                    Toast.makeText(ManageFlowers.this, "Please enter the Flower price", Toast.LENGTH_SHORT).show();
                else if (descriptionTXT.equals(""))
                    Toast.makeText(ManageFlowers.this, "Please enter the Flower Description", Toast.LENGTH_SHORT).show();

                else {

                    Boolean checkFinsertdata = DB.insertflowerData(nameTXT, priceTXT, descriptionTXT, categoryTXT,idTXT);

                    if (checkFinsertdata == true)
                        Toast.makeText(ManageFlowers.this, "Flower inserted ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(ManageFlowers.this, "Entry failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        UpdateF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = FlowerName.getText().toString();
                String priceTXT = FlowerPrice.getText().toString();
                String descriptionTXT = FlowerDescription.getText().toString();
                String idTXT = Flowerid.getText().toString();

                if (nameTXT.equals("") && priceTXT.equals("") && descriptionTXT.equals("") && idTXT.equals(""))
                    Toast.makeText(ManageFlowers.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();
                else {

                    Boolean check_C_updatedata = DB.updateflowerdata(nameTXT, priceTXT, descriptionTXT, descriptionTXT,idTXT);

                    if (check_C_updatedata == true)
                        Toast.makeText(ManageFlowers.this, "Flower Updated ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(ManageFlowers.this, "Entry failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


        DeleteF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryidTXT = Flowerid.getText().toString();

                if ((categoryidTXT.equals(""))){
                    Toast.makeText(ManageFlowers.this, "Please Fill the filled", Toast.LENGTH_SHORT).show();}
                else {
                    Boolean delete_C_deletedata = DB.deleteflowerdata(categoryidTXT);

                    if (delete_C_deletedata == true)
                        Toast.makeText(ManageFlowers.this, "Flower Deleted ", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(ManageFlowers.this, "Entry failed", Toast.LENGTH_SHORT).show();
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