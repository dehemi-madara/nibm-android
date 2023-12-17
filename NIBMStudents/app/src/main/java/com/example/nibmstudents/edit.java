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

public class edit extends AppCompatActivity {

    EditText edN,edA,edG,edM,edP,edR;
    Button bE,bD,bB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edN = findViewById(R.id.txtSName);
        edA = findViewById(R.id.txtAge);
        edG = findViewById(R.id.txtGender);
        edM = findViewById(R.id.txtMobNum);
        edP = findViewById(R.id.txtPNum);
        edR = findViewById(R.id.txtReg);

        bE = findViewById(R.id.btnEdit);
        bD = findViewById(R.id.btnDelete);
        bB = findViewById(R.id.btnBack);

        Intent i = getIntent();

        String t1 = i.getStringExtra("reg").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("age").toString();
        String t4 = i.getStringExtra("gender").toString();
        String t5 = i.getStringExtra("mobile").toString();
        String t6 = i.getStringExtra("parent").toString();

        edR.setText(t1);
        edN.setText(t2);
        edA.setText(t3);
        edG.setText(t4);
        edM.setText(t5);
        edP.setText(t6);


        bB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view.class);
                startActivity(i);
            }
        });

        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();

            }
        });


        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();

            }
        });
    }

    public void Edit(){
        try {
            String name = edN.getText().toString();
            String age = edA.getText().toString();
            String gender = edG.getText().toString();
            String mobile = edM.getText().toString();
            String parent = edP.getText().toString();
            String Register = edR.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);

            String sql = "update records set name = ?,age = ?,gender = ?,mobile = ?,parent = ? where reg = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,age);
            statement.bindString(3,gender);
            statement.bindString(4,mobile);
            statement.bindString(5,parent);
            statement.bindString(6,Register);
            statement.execute();
            Toast.makeText(this, "Record Updated...!", Toast.LENGTH_SHORT).show();

            edN.setText("");
            edA.setText("");
            edG.setText("");
            edM.setText("");
            edP.setText("");
            edN.requestFocus();

        }
        catch (Exception ex){
            Toast.makeText(this, "Record Update Fail...!", Toast.LENGTH_SHORT).show();

        }
    }

    public void Delete(){
        try {

            String Register = edR.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);

            String sql = "delete from records where reg = ? ";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,Register);
            statement.execute();
            Toast.makeText(this, "Record Deleted...!", Toast.LENGTH_SHORT).show();

            edN.setText("");
            edA.setText("");
            edG.setText("");
            edM.setText("");
            edP.setText("");
            edN.requestFocus();

        }
        catch (Exception ex){
            Toast.makeText(this, "Record Delete Fail...!", Toast.LENGTH_SHORT).show();

        }
    }
}