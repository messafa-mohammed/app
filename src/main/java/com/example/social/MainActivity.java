package com.example.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper userAdapter;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAdapter = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button login = findViewById(R.id.btn1);
        Button sign = findViewById(R.id.btn2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pword = password.getText().toString().trim();
                Cursor cursor = userAdapter.getUserData(name, pword);
                if (name.isEmpty() || pword.isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (cursor != null && cursor.moveToFirst()) {
                    String username = cursor.getString(cursor.getColumnIndexOrThrow("v_username"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("v_email"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("v_phone"));

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }


            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }


}