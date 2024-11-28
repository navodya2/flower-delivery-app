package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class getstarted extends AppCompatActivity {

    Button nextbutton;
    TextView sKip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        nextbutton = findViewById(R.id.nxtbtn);
        sKip = findViewById(R.id.skip);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nxtpage = new Intent(getApplicationContext(), getStartedPage1.class);
                startActivity(nxtpage);

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