package com.boxeelab.playground.simplethread.simplethread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * This application demonstrate simple java thread.
 */

public class MainActivity extends AppCompatActivity {


    private static final String APP_DEBUG = "SIMPLE_THREAD_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
     * This method is called using android:onClick from activity_main.xml
     * @param view
     */
    public void onClick(View view)
    {
        runSimpleThread();
    }

    /**
     *  Thread does not do anything. runs a for to simulate a long task
     */
    public void runSimpleThread()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // running simple loop here  with a 100 millisecond delay
                for(int i =0 ;i<1000;i++)
                {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(APP_DEBUG,"Running simple thread");
                }

            }
        });
        thread.start();

    }
}
