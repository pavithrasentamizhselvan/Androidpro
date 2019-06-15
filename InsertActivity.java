package com.example.pavithra.credit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.pavithra.credit.DatabaseHelper.TABLE_NAME;

public class InsertActivity extends AppCompatActivity {
    EditText amount;
    Button update1;
    DatabaseHelper help=new DatabaseHelper(this);
    SQLiteDatabase db;
    String uname;int amount1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        update1 = (Button) findViewById(R.id.insertbtn1);
        amount=(EditText)findViewById(R.id.amount);

        db = help.getWritableDatabase();
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=getIntent().getExtras().getString("username");
                amount1 = Integer.parseInt(amount.getText().toString());
                System.out.print("Problem here click");
                update(uname, amount1);
                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_LONG).show();
            }


  private void update(String usname, int amount11) {
        ContentValues content=new ContentValues();
				Cursor cur=db.rawQuery("select balance from registration where username=?",new String[]{usname});
////              System.out.print("Problem here");
             int bal=cur.getInt(1) + amount11;
////            // cur.moveToNext();
////              content.put(DatabaseHelper.USER_NAME,"SWAMIJI");
      content.put(DatabaseHelper.USER_BALANCE,bal);
//db.execSQL("UPDATE registration SET balance="+amount1+" WHERE username="+uname);
		db.update(DatabaseHelper.TABLE_NAME,content,"username=?",new String[]{usname});
      //db.rawQuery("UPDATE registration SET balance=1000 WHERE username=Swami",null);
            }

        });

    }}
