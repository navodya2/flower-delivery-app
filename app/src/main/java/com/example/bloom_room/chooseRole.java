package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class chooseRole extends AppCompatActivity {


    private RadioGroup radioGroup;
    private RadioButton radioAdmin;
    private RadioButton radioUser;
    private Button radioSubmitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);


        radioGroup = findViewById(R.id.radioGroup);
        radioAdmin = findViewById(R.id.radioAdmin);
        radioUser = findViewById(R.id.radioUser);
        radioSubmitBtn = findViewById(R.id.radioSubmitBtn);

        // Set click listener for the "Start" button
        radioSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check which radio button is selected
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    // No radio button is selected
                    Toast.makeText(chooseRole.this, "Please select one", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedId == R.id.radioAdmin) {
                        // Redirect to the admin login page
                        startActivity(new Intent(chooseRole.this, Adminlogin.class));
                    } else if (selectedId == R.id.radioUser) {
                        // Redirect to the user login page
                        startActivity(new Intent(chooseRole.this, login.class));
                    }
                }
            }
        });
    }
}