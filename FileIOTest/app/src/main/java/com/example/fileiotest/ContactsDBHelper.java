package com.example.fileiotest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ContactsDBHelper extends SQLiteOpenHelper {
    public ContactsDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //데이터베이스가 처음 생성될 때만 호출된다
        String sql = "create table mycontacts ( ";
        sql += "_id integer primary key autoincrement, ";
        sql += "name text, ";
        sql += "phone text";
        sql += " )";

        Log.i("MainActivity", sql);

        db.execSQL(sql);

        //리스트 뷰 사용을 위해
        // dummy data
        for (int i = 1; i < 100; i++) {
            sql = "";
            sql += "insert into mycontacts values ( ";
            sql += "null" + ", ";
            sql += "'홍길동" + i + "', ";
            sql += "'010-" + i + "'";
            sql += " )";

            Log.i("MainActivity", sql);

            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists mycontacts");
        onCreate(db);
    }
}
