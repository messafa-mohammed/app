package com.example.social;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {
    UserAdapter userAdapter;
    EditText username , password , phone , email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button login = findViewById(R.id.btn4);
        Button sign = findViewById(R.id.btn3);

        username = findViewById(R.id.s_username);
        password = findViewById(R.id.s_password);
        phone = findViewById(R.id.s_phone);
        email = findViewById(R.id.s_email);
        userAdapter = new UserAdapter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void registerUser() {
        String v_username = username.getText().toString().trim();
        String v_password = password.getText().toString().trim();
        String v_email = email.getText().toString().trim();
        String v_phone = phone.getText().toString().trim();

        if (v_username.isEmpty() || v_password.isEmpty() || v_email.isEmpty() || v_phone.isEmpty() ) {
            Toast.makeText(this, "Please enter both first name and last name.", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = userAdapter.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("v_username", v_username);
        values.put("v_password", v_password);
        values.put("v_email", v_email);
        values.put("v_phone", v_phone);

        long newRowId = db.insert("Users", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Student registered successfully.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error registering student.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }




}