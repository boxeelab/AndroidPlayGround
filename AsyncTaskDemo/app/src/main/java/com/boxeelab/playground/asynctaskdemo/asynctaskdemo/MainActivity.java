package com.boxeelab.playground.asynctaskdemo.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * This application demonstrate asynctask
 * doInBackground execute does not run on ui thread
 * consider onpause to cancel the thread
 * check for oncancel inside doInBackGroud
 */
public class MainActivity extends AppCompatActivity {

    public static final String APP_DEBUG = "ASYNC_THREAD_DEBUG";
    private ProgressBar progressBar;
    private AsyncThreadDemo asyncThreadDemo;
    private boolean isRunning = false;

    @Override
    protected void onPause() {
        super.onPause();
        if(isRunning && asyncThreadDemo!= null)
        {
            asyncThreadDemo.cancel(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(20);
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
     * Getter for progress bar will be used inside the asyntask object
     *
     * @return
     */

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * This method is called using android:onClick from activity_main.xml
     *
     * @param view
     */
    public void onClick(View view) {
        if (view.getId() == R.id.btnStartThread) {
            runAsyncThread();
        } else {
            cancelThread();
        }
    }

    private void runAsyncThread() {
        if (!isRunning) {
            asyncThreadDemo = new AsyncThreadDemo();
            asyncThreadDemo.execute(100);
            isRunning = true;
        }
    }

    private void cancelThread() {
        asyncThreadDemo.cancel(true);
    }

    /**
     * Async thread demo class
     */

    class AsyncThreadDemo extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            MainActivity.this.isRunning = false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // update  progress bas here
            ProgressBar progressBar = MainActivity.this.getProgressBar();
            if (progressBar != null) {
                progressBar.setProgress(values[0]);
            }
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            MainActivity.this.isRunning = false;
            Toast.makeText(MainActivity.this, "Async Task debug Thread canceled", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            MainActivity.this.isRunning = false;
        }

        /**
         * This method runs in a different thread other function runs in UI thread
         *
         * @param params
         * @return
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer j[] = {i};
                this.publishProgress(j);


                if(isCancelled())
                {
                    break;
                }
            }
            return null;
        }
    }



}
