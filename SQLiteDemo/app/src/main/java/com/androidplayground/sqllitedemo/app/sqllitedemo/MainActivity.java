package com.androidplayground.sqllitedemo.app.sqllitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDemo sqlLiteDemoDBHelper = new SQLiteDemo(this);
    }
}
