package com.androidplayground.sqllitedemo.app.sqllitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

/**
 * Created by dhruba on 3/2/16.
 */
public class DBTest extends AndroidTestCase {

    // Since we want each test to start with a clean slate
    void deleteTheDatabase() {
        mContext.deleteDatabase(SQLiteDemo.DATABASE_NAME);
    }

    /*
        This function gets called before each test is executed to delete the database.  This makes
        sure that we always have a clean test.
     */
    public void setUp() {
        deleteTheDatabase();
    }

    public void testCreateDatabase() throws Throwable
    {
        // this function will delete to make sure we have the database removed to make sure database creation is
        // is working fine

        //Test database creation
        //Check if all the tables got created


        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add("TEST_TABLE");

        // delete database to make sure we can check the database creation
        mContext.deleteDatabase(SQLiteDemo.DATABASE_NAME);
        SQLiteDatabase db = new SQLiteDemo(mContext).getWritableDatabase();

        assertTrue("Not able to open database",db.isOpen());

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master where type = 'table'",null);
        assertTrue("Database not created properly", cursor.moveToFirst());

        // check if the all tables are created properly
        do{
            tableNameHashSet.remove(cursor.getString(cursor.getColumnIndex("name")));
        }while(cursor.moveToNext());

        if(!cursor.isClosed())
        {
            cursor.close();
        }

        assertTrue("All tables are not created",tableNameHashSet.isEmpty());

        // check if all the columns are created

        cursor = db.rawQuery("PRAGMA table_info ( 'TEST_TABLE' )",null);
        assertTrue("not able to query table information",cursor.moveToFirst());

        final HashSet<String> tableColumnInformation = new HashSet<String>();

        tableColumnInformation.add("_id");
        tableColumnInformation.add("COLUMN1");
        tableColumnInformation.add("COLUMN2");

        do{
            tableColumnInformation.remove(cursor.getString(cursor.getColumnIndex("name")));
        }while(cursor.moveToNext());

        assertTrue("Does not contain all the columns requires",tableColumnInformation.isEmpty());
        db.close();
     }



    public void testWeatherTable() {

       // assertFalse( "Error: More than one record returned from weather query",
               // weatherCursor.moveToNext() );
        assertTrue("Test pass",true);

    }



    public void testInsertRecordInDB() throws Throwable
    {
        mContext.deleteDatabase(SQLiteDemo.DATABASE_NAME);
        SQLiteDatabase db = new SQLiteDemo(mContext).getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("COLUMN1","value1");
        contentValues.put("COLUMN2", "value2");

        long id = db.insert("TEST_TABLE",null,contentValues);
        assertTrue("Insert worked",id>0);
    }

}
