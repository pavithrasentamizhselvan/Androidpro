package com.example.pavithra.credit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.pavithra.credit.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
DatabaseHelper help;
    TextView txt;
    SQLiteDatabase db;
    Button btnview;
    ListView listuser;String[] users={"Pavi","Swami","Viji","Tamil"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        help = new DatabaseHelper(this);
        db = help.getWritableDatabase();

        listuser = (ListView) findViewById(R.id.listuser);
        ArrayAdapter<String> arr=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        listuser.setAdapter(arr);
        listuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Clicked "+users[position], Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getApplicationContext(),InsertActivity.class);
                in.putExtra("username",users[position]);
                startActivity(in);
            }
        });


    }

    public void insert(View view) {
        help.insert();
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    }

    public void view(View view) {
        Cursor cur=db.rawQuery("select * from registration",null);
        txt=(TextView)findViewById(R.id.txt);int i=0;
        while (cur.moveToNext()){
            txt.setText(cur.getString(i)+" "+cur.getInt(i+1)+"\n");
            i++;
        }
    }
}
