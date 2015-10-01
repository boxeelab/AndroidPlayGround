package com.boxeelab.playground.httpdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {

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

    public void httpGetRequest()
    {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            readStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void httpPostRequest()
    {
        try {
            String urlParameters  = "param1=a&param2=b&param3=c";
            byte[] postData       = urlParameters.getBytes("UTF-8"); //"UTF-8"  StandardCharsets.UTF_8
            URL url = new URL("http://www.google.com");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput( true );
            httpURLConnection.setInstanceFollowRedirects( false );
            httpURLConnection.setRequestMethod( "POST" );
            httpURLConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty( "charset", "utf-8");
            httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( urlParameters.length() ));
            httpURLConnection.setUseCaches( false );
            // Use this for newer api
            // try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
            //                wr.write( postData );
            //            }
            // use this below kitkat
            OutputStream wr =  httpURLConnection.getOutputStream();
            wr.write(postData);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStream(InputStream inputStream)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer("");
        String val  ="";
        try {
            while((val  = bufferedReader.readLine())!=null)
            {
                stringBuffer.append(val);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
