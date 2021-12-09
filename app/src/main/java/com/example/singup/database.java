package com.example.singup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private static final String mydatabase= "student";
    public static final int VERSION=1;

    public database(@Nullable Context context) {
        super(context, mydatabase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query ="Create table student(_id Integer primary key autoincrement ,Username text,Email text, Password text)";
db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student");
        onCreate(db);


    }
    public boolean putData(String Name, String Email, String Password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues content=new ContentValues();

        content.put("username",Name);
        content.put("Email",Email);
        content.put("password",Password);
      long response = sqLiteDatabase.insert("student",null,content);
        if (response==-1) {
            return false;

        }else {
            return true;

        }

    }

    public Cursor viewdata(){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select *from student",null);

        return cursor ;

    }
}

