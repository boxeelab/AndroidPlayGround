package com.boxeelab.playground.xmlparsedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
  xml reader
 */
public class MainActivity extends AppCompatActivity {

    private static final String APP_DEBUG = "XML_READER_DOCUMENT";

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

    public void onClick(View view)
    {
        parseXmlData("<xmldoc>test</xmldoc>");
    }

    private void parseXmlData(String xmlData)
    {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            xmlPullParser.setInput(reader);
            int eventType = xmlPullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType == XmlPullParser.START_TAG)
                {
                    Log.d(APP_DEBUG,xmlPullParser.getName());
                }
                else if(eventType ==XmlPullParser.END_DOCUMENT)
                {
                    Log.d(APP_DEBUG,xmlPullParser.getName());

                }else if(eventType == XmlPullParser.TEXT)
                {
                    Log.d(APP_DEBUG,xmlPullParser.getText());
                }

                eventType = xmlPullParser.getEventType();
            }


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
