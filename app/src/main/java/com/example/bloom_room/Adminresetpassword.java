package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminresetpassword extends AppCompatActivity {
    Button submit;
    EditText name, Pass, rePass;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminresetpassword);
        submit = findViewById(R.id.reset);
        name = findViewById(R.id.rename);
        Pass = findViewById(R.id.repass);
        rePass = findViewById(R.id.recpass);
        DB = new DBHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUsername = name.getText().toString();
                String newPassword = Pass.getText().toString();
                String newrepass = rePass.getText().toString();
                String email = getIntent().getStringExtra("email");


                if (newUsername.equals("") && newPassword.equals("") && newrepass.equals("")){
                    Toast.makeText(Adminresetpassword.this, "please fill out these fields", Toast.LENGTH_SHORT).show();
                }
                else if (newUsername.equals("")){
                    Toast.makeText(Adminresetpassword.this, "please enter new user name ", Toast.LENGTH_SHORT).show();
                } else if (newPassword.equals("")) {
                    Toast.makeText(Adminresetpassword.this, "please enter your password", Toast.LENGTH_SHORT).show();
                } else if (newrepass.equals("")) {
                    Toast.makeText(Adminresetpassword.this, "please enter confirm password", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (newPassword.equals(newrepass)) {
                        Boolean insert1 = DB.resetPassword(email, newUsername, newPassword);

                        if (insert1){
                            Toast.makeText(getApplicationContext(), "Reset succfull", Toast.LENGTH_SHORT).show();
                            Intent test1 = new Intent(getApplicationContext(), Adminlogin.class);
                            startActivity(test1);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        else {
                            Toast.makeText(Adminresetpassword.this, " reset faild Try again", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Adminresetpassword.this, "password not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}