package com.example.social;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity  extends AppCompatActivity {
    private TextView username;
    private TextView gmail;
    private TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = findViewById(R.id.p_name);
        gmail = findViewById(R.id.p_email);
        phone = findViewById(R.id.p_phone);
        Intent intent = getIntent();




                String usn=getIntent().getStringExtra("username");
                String pho=getIntent().getStringExtra("phone");
                String em=getIntent().getStringExtra("email");
                username.setText(usn);
                gmail.setText(em);
                phone.setText(pho);



    }
}