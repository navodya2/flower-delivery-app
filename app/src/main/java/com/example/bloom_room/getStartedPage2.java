package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class getStartedPage2 extends AppCompatActivity {
    TextView sKip;
    Button nxtbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_page2);

        nxtbutton = findViewById(R.id.nxtbtn);
        sKip = findViewById(R.id.skip);

        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextpage = new Intent(getApplicationContext(), getStartedPage3.class);
                startActivity(nextpage);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });


        sKip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logpage = new Intent(getApplicationContext(), chooseRole.class);
                startActivity(logpage);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}