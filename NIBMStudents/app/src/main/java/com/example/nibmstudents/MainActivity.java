package com.example.nibmstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUserN);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this, "Login Successfull...!", Toast.LENGTH_SHORT).show();
                    openregister();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openregister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}