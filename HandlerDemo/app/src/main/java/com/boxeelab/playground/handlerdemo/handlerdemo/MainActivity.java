package com.boxeelab.playground.handlerdemo.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static final String APP_DEBUG = "HANDLER_THREAD_DEBUG";
    public static final int MSG_TYPE_1 = 1;
    public static final int MSG_TYPE_2 = 2;
    HandlerDemo handlerDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerDemo = new HandlerDemo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler is executed inside the thread it was created. To demonstrate that
     * I am creating message inside a separate thread
     *
     * @param view
     */
    public void onClick(View view) {

        final View v =view;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (v.getId() == R.id.btnAddNewMsg) {
                    addNewMessage();
                } else {
                    addNewRunnable();
                }
            }
        });
        thread.start();

    }

    public void addNewMessage() {
        Message msg = handlerDemo.obtainMessage(MSG_TYPE_1);
        msg.arg1 = 1;
        // Use bundle to set additional data in message object
        //Bundle bundle = new Bundle();
        //msg.setData(bundle);
        handlerDemo.handleMessage(msg);
    }

    public void addNewRunnable() {

        handlerDemo.post(new Runnable() {
            @Override
            public void run() {
                Log.d(APP_DEBUG, "Runnable is executed here");
            }
        });
    }


    class HandlerDemo extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_TYPE_1:
                    Log.d(APP_DEBUG, "Process message case 1");
                    break;
                case MSG_TYPE_2:
                    Log.d(APP_DEBUG, "Process message case 2");
                    break;
                default:
                    Log.d(APP_DEBUG, "Some othe type message");
                    break;


            }
        }
    }


}
