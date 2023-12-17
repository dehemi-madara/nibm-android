package com.example.nibmstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);
        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from records",null);
        int reg = c.getColumnIndex("reg");
        int name = c.getColumnIndex("name");
        int age = c.getColumnIndex("age");
        int gender = c.getColumnIndex("gender");
        int mobile = c.getColumnIndex("mobile");
        int parent = c.getColumnIndex("parent");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<student>();

        if (c.moveToFirst()){
            do {
                student stu = new student();
                stu.reg = c.getString(reg);
                stu.name = c.getString(name);
                stu.age = c.getString(age);
                stu.gender = c.getString(gender);
                stu.mobile = c.getString(mobile);
                stu.parent = c.getString(parent);

                stud.add(stu);

                titles.add(c.getString(reg) + " \t " + c.getString(name) + " \t " + c.getString(age) + " \t " + c.getString(gender) + " \t " + c.getString(mobile) + " \t " + c.getString(parent));


            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();

                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), edit.class);

                i.putExtra("reg",stu.reg);
                i.putExtra("name",stu.name);
                i.putExtra("age",stu.age);
                i.putExtra("gender",stu.gender);
                i.putExtra("mobile",stu.mobile);
                i.putExtra("parent",stu.parent);
                startActivity(i);
            }
        });


    }
}