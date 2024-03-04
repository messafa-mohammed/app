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
    UserAdapter userAdapter;
    EditText username , password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.btn1);
        Button sign = findViewById(R.id.btn2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = findViewById(R.id.username);
                password = findViewById(R.id.password);
                String name = username.getText().toString().trim();
                String pword = password.getText().toString().trim();
//                System.out.println(name + " "+pword);

                User flat = getUser(name,pword);
                if(flat != null){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("username" , flat.getUsername());
                    intent.putExtra("email" , flat.getEmail());
                    intent.putExtra("phone" , flat.getPhone());
                    startActivity(intent);


                }

            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
    }


    private User getUser(String username, String password) {
        SQLiteDatabase db = userAdapter.getReadableDatabase();
        String[] projection = {"v_username", "v_password", "v_email", "v_phone"};
        String selection = "v_username = ? AND v_password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(
                "Users",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        User user = null;
        if (cursor.moveToFirst()) {
            String v_username = cursor.getString(cursor.getColumnIndexOrThrow("v_username"));
            String v_password = cursor.getString(cursor.getColumnIndexOrThrow("v_password"));
            String v_email = cursor.getString(cursor.getColumnIndexOrThrow("v_email"));
            String v_phone = cursor.getString(cursor.getColumnIndexOrThrow("v_phone"));
            user = new User(v_username, v_password, v_email, v_phone);
        }

        cursor.close();
        db.close();

        return user;
    }




}