package com.example.mynote;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NoteDB noteDB;
    private SQLiteDatabase dbReader;
    private Button textbtn, imgbtn, videobtn;
    private ListView lv;
    private Intent intent;
    private MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.list);
        textbtn = (Button) findViewById(R.id.text);
        imgbtn = (Button) findViewById(R.id.img);
        videobtn = (Button) findViewById(R.id.video);

        textbtn.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
        videobtn.setOnClickListener(this);

        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this, AddContent.class);
        switch (v.getId()) {
            case R.id.text:
                intent.putExtra("flag", "1");
                startActivity(intent);
                break;

            case R.id.img:
                intent.putExtra("flag", "2");
                startActivity(intent);
                break;

            case R.id.video:
                intent.putExtra("flag", "3");
                startActivity(intent);
                break;
        }
    }

    public void selectDB() {
        Cursor cursor = dbReader.query(NoteDB.TABLE_NAME, null, null,null, null, null, null);
        adapter = new MyAdapter(this,cursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }
}
