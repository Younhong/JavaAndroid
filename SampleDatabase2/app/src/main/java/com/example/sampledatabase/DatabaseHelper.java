package com.example.sampledatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String name = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("onCreate()");
        String sql = "create table if not exists emp(" +
                " _id integer PRIMARY KEY autoincrement, " +
                " name text, " + " age integer, " + " mobile text)";
        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
        println("onOpen()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade(): " + oldVersion + "->" + newVersion);
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data) {
        Log.d("Database Helpger", data);
    }
}
