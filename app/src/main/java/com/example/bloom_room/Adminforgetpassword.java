package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminforgetpassword extends AppCompatActivity {

    EditText enteremail;
    Button submit;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminforgetpassword);

        enteremail = findViewById(R.id.enteremaiL);
        submit = findViewById(R.id.submit);
        DB = new DBHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = enteremail.getText().toString();


                if (enteremail.equals("")) {
                    Toast.makeText(Adminforgetpassword.this, "please enter Email address", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkemail = DB.checkemail(email);
                    if (checkemail == true) {
                        Intent click = new Intent(getApplicationContext(), Adminresetpassword.class);
                        click.putExtra("email", email);
                        startActivity(click);
                    } else {
                        Toast.makeText(Adminforgetpassword.this, "Sorry, we couldn't find any account associated with that email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}