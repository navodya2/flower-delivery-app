package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {

    Button login;
    EditText username,password;
    TextView forget,siginup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        login = findViewById(R.id.logbtn);
        username = findViewById(R.id.usernamE);
        password = findViewById(R.id.passworD);
        DB = new DBHelper(this);
        forget = findViewById(R.id.forgeT);


//forget username and password
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foget = new Intent(getApplicationContext(), Adminforgetpassword.class);
                startActivity(foget);
            }
        });

        // loginpage to sigin page
//        siginup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Create = new Intent(getApplicationContext(), AdminSigup.class);
//                startActivity(Create);
//
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//
//
//        });

// login function (checking user and password) , check if the fiels are empty
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") && pass.equals(""))
                    Toast.makeText(Adminlogin.this, "Please Enter the user name and password", Toast.LENGTH_SHORT).show();
                else if (user.equals(""))
                    Toast.makeText(Adminlogin.this, "Please enter the user name", Toast.LENGTH_SHORT).show();
                else if (pass.equals(""))
                    Toast.makeText(Adminlogin.this, "Please enter the password", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkuserpass = DB.checkAdminusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(Adminlogin.this, " successfully Login", Toast.LENGTH_SHORT).show();
                        Intent text = new Intent(getApplicationContext(), Adminpanel.class);
                        startActivity(text);
                    }else {
                        Toast.makeText(Adminlogin.this, "Invalid Credenials", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });



    }
}