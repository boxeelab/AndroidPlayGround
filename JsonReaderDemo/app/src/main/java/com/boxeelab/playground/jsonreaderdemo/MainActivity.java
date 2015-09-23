package com.boxeelab.playground.jsonreaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String APP_DEBUG = "JSON_READ_DEBUG";

    //add a sample json here to read

    //sample json is taken from
    //http://developer.android.com/reference/android/util/JsonReader.html
    String sampleJson = "[\n" +
            "   {\n" +
            "     \"id\": 912345678901,\n" +
            "     \"text\": \"How do I read JSON on Android?\",\n" +
            "     \"geo\": null,\n" +
            "     \"user\": {\n" +
            "       \"name\": \"android_newb\",\n" +
            "       \"followers_count\": 41\n" +
            "      }\n" +
            "   },\n" +
            "   {\n" +
            "     \"id\": 912345678902,\n" +
            "     \"text\": \"@android_newb just use android.util.JsonReader!\",\n" +
            "     \"geo\": [50.454722, -104.606667],\n" +
            "     \"user\": {\n" +
            "       \"name\": \"jesse\",\n" +
            "       \"followers_count\": 2\n" +
            "     }\n" +
            "   }\n" +
            " ]}";


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
        readJason(sampleJson);
    }

    /**
     * JasonReader skip value can skip entire object or just a value
     * to write genereic json read use peek function and match with JsonToken constant.
     *
     * @param json
     */
    private void readJason(String json)
    {
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
        jsonReader.setLenient(true);
        try {
            jsonReader.beginArray();
            while(jsonReader.hasNext())
            {
                jsonReader.beginObject();
                Log.d(APP_DEBUG, "Statring of the object");
                while(jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    Log.d(APP_DEBUG, name);
                    if(name.equals("id"))
                    {
                        jsonReader.nextLong();
                    }else if(name.equals("text"))
                    {
                        jsonReader.nextString();
                    }
                    else if(name.equals("geo") && jsonReader.peek() != JsonToken.NULL)
                    {
                        jsonReader.skipValue();
                        
                    }
                    else if(name.equals("user"))
                    {
                        jsonReader.skipValue();
                    }
                    else
                    {
                        jsonReader.skipValue();
                    }

                }

                jsonReader.endObject();

            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
