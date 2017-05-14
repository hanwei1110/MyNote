package com.example.mynote;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.value;

/**
 * Created by 彦祖 on 2017/5/12.
 */

public class AddContent extends Activity implements View.OnClickListener{

    private String val;
    private Button savebtn,deletebtn;
    private EditText ettext;
    private ImageView c_img;
    private VideoView c_video;
    private NoteDB noteDB;
    private SQLiteDatabase dbWriter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");

        savebtn = (Button) findViewById(R.id.btn_save);
        deletebtn = (Button) findViewById(R.id.btn_delete);
        ettext = (EditText) findViewById(R.id.ettext);
        c_img = (ImageView) findViewById(R.id.c_img);
        c_video = (VideoView) findViewById(R.id.c_video);

        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        noteDB = new NoteDB(this);
        dbWriter = noteDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                addDB();
                finish();
                break;
            case R.id .btn_delete:
                finish();
                break;

        }
    }
    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,ettext.getText().toString());
        cv.put(NoteDB.TIME,getTime());
        dbWriter.insert(NoteDB.TABLE_NAME,null,cv);
    }
    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }

}
