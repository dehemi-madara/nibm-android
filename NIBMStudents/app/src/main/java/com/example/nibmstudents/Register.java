package com.example.nibmstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText edN,edA,edG,edM,edP;
    Button bR,bV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edN = findViewById(R.id.txtSName);
        edA = findViewById(R.id.txtAge);
        edG = findViewById(R.id.txtGender);
        edM = findViewById(R.id.txtMobNum);
        edP = findViewById(R.id.txtPNum);

        bR = findViewById(R.id.btnRegister);
        bV = findViewById(R.id.btnView);

        bV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();

            }
        });
    }
    public void insert(){
        try {
            String name = edN.getText().toString();
            String age = edA.getText().toString();
            String gender = edG.getText().toString();
            String mobile = edM.getText().toString();
            String parent = edP.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(reg INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,age VARCHAR,gender VARCHAR,mobile VARCHAR,parent VARCHAR)");

            String sql = "insert into records(name,age,gender,mobile,parent)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,age);
            statement.bindString(3,gender);
            statement.bindString(4,mobile);
            statement.bindString(5,parent);
            statement.execute();
            Toast.makeText(this, "Record Added...!", Toast.LENGTH_SHORT).show();

            edN.setText("");
            edA.setText("");
            edG.setText("");
            edM.setText("");
            edP.setText("");
            edN.requestFocus();

        }
        catch (Exception ex){
            Toast.makeText(this, "Record Fail...!", Toast.LENGTH_SHORT).show();

        }
    }
}