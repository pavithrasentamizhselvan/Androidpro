package com.example.pavithra.loginapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
     DatabaseHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg, _btnlogin;
    Cursor cursor;
    EditText _txtuname, _txtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        openHelper = new DatabaseHelper(this);
        _txtuname = (EditText) findViewById(R.id.txtuname);
        _txtpass = (EditText) findViewById(R.id.txtpass);
        _btnlogin = (Button) findViewById(R.id.btnlogin);
        _btnreg = (Button) findViewById(R.id.btnreg);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String uname = _txtuname.getText().toString();
                String pass = _txtpass.getText().toString();
                insertdata(uname, pass);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
            }

            private void insertdata(String uname, String pass) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.USER_NAME, uname);
                contentValues.put(DatabaseHelper.PASS_WORD, pass);
                long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
            }

        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = _txtuname.getText().toString();
                String pass = _txtpass.getText().toString();
                try{
                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.USER_NAME + "=? AND " + DatabaseHelper.PASS_WORD + "=?", new String[]{uname, pass});
               //cursor=db.rawQuery("SELECT * FROM registration WHERE username=? AND pass=?",new String[]{uname,pass});
                if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                    } else {
                    Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }}catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                }


            }
        });
//                Toast.makeText(Register.this, "Login", Toast.LENGTH_SHORT).show();
//            }
//        });
    }}
       



     
