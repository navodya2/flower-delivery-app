package com.example.bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {

    Button signup;
    EditText username,Email,Password,cpassword;
    TextView signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_up);

        signup = findViewById(R.id.signbtn);
        username = findViewById(R.id.userN);
        Password = findViewById(R.id.passwworD);
        Email = findViewById(R.id.emaiL);
        cpassword = findViewById(R.id.cpassworD);
        signin = findViewById(R.id.signiN);
        DB = new DBHelper(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forget = new Intent(getApplicationContext(), login.class);
                startActivity(forget);

                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String Cpassword = cpassword.getText().toString();

                //for Empty values
                if (user.equals("")) {
                    Toast.makeText(signUp.this, "Please enter username", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(signUp.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(signUp.this, "please enter password", Toast.LENGTH_SHORT).show();

                } else if (Cpassword.equals("")) {
                    Toast.makeText(signUp.this, "please enter confirm password", Toast.LENGTH_SHORT).show();

                } else {


                    if (password.equals(Cpassword)) {
                        Boolean checkeusermail = DB.checkemail(email);//check if the  email already exits
                        if (checkeusermail == false) {
                            Boolean checkuser = DB.checkusername(user);
                            if (checkuser == false) {

                                Boolean insert = DB.insertData(user, email, password);//incerting the data in to databace
                                if (insert) {
                                    Toast.makeText(signUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent test1 = new Intent(getApplicationContext(), login.class);
                                    startActivity(test1);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                                } else {

                                    Toast.makeText(signUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(signUp.this, " User name is already taken please choose different one ", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(signUp.this, "Email is already in use, please login ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signUp.this, "Please check the password", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });



    }
}