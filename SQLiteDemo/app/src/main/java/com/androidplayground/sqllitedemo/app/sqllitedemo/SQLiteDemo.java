package com.androidplayground.sqllitedemo.app.sqllitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dhruba on 3/2/16.
 */
public class SQLiteDemo extends SQLiteOpenHelper {

    String mCreateTable = "CREATE TABLE TEST_TABLE ( _id INTEGER PRIMARY KEY ,COLUMN1 TEXT , COLUMN2 TEXT )";
    static final int mVersion = 1;
    static final String DATABASE_NAME = "test.db";



    public SQLiteDemo(Context context) {
        super(context, SQLiteDemo.DATABASE_NAME, null, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
