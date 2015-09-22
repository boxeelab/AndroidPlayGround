package playground.boxeelab.com.filereadwritedemo;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * File read write demo explores the basic file read write operation
 * Second file application will demonstrte directory browsing and other example
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String APP_DEBUG = "FILE_READ_WRITE_DEMO";

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
        // METHOD 1
        //writeBasicFile1();

        // METHOD 2
        //writeBasicFile();

        //readBasicFile();

        directoryOperation();
    }

    /**
     * Creating file in UI thread is not a good idea. We should always create it in a separate thread.
     * For simplicity I am creating file in UI thread.
     *
     */

    public void writeBasicFile1() {
        String name = "test1.txt";
        String path = getFilesDir().getPath();
        String sampleContent = "Test content for file";

        File file = new File(path,name);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(sampleContent.getBytes(), 0, sampleContent.getBytes().length);
            fileOutputStream.close();
            Log.d(APP_DEBUG, "File create successfully" + file.getAbsolutePath());
        } catch (IOException ioexp) {
            Log.e(APP_DEBUG, ioexp.getMessage());
        }
    }

    /**
     * Uncomment below method to try diffrent way of opening a file
     */

//    public void writeBasicFile2() {
//        String name = "test.txt";
//        String path = getFilesDir().getPath();
//        String sampleContent = "Test content for file";
//
//        //File file = new File(name, path);
//        try {
//            FileOutputStream fileOutputStream = openFileOutput(name,Context.MODE_APPEND);
//            fileOutputStream.write(sampleContent.getBytes(), 0, sampleContent.getBytes().length);
//            fileOutputStream.close();
//            Log.d(APP_DEBUG, "File create successfully");
//        } catch (IOException ioexp) {
//            Log.e(APP_DEBUG, ioexp.getMessage());
//        }
//    }



    public void readBasicFile() {
        String name = "test1.txt";
        String path = getFilesDir().getPath();
        File file = new File(path,name);
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder fileContent = new StringBuilder();
            Log.d(APP_DEBUG,"outside while loop");
            String fileString = "";
            while((fileString = bufferedReader.readLine())!=null) {
                Log.d(APP_DEBUG, "inside while loop");
                fileContent.append(fileString);
            }
            Log.d(APP_DEBUG,fileContent.toString());
        } catch (FileNotFoundException e) {
            Log.e(APP_DEBUG, e.getMessage());
        } catch (IOException e) {
            Log.e(APP_DEBUG, e.getMessage());
        }


    }

    public void writeFileToCache() {
        String name = "test1.txt";
        String path = getCacheDir().getPath();
        String sampleContent = "Test content for file";

        File file = new File(path,name);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(sampleContent.getBytes(), 0, sampleContent.getBytes().length);
            fileOutputStream.close();
            Log.d(APP_DEBUG, "File create successfully" + file.getAbsolutePath());
        } catch (IOException ioexp) {
            Log.e(APP_DEBUG, ioexp.getMessage());
        }
    }

    public void readFileFromCache() {

    }

    public void directoryOperation() {
        File dir = Environment.getExternalStorageDirectory();
        Log.d(APP_DEBUG,"Outside  if block");
        if(dir.isDirectory())
        {
            Log.d(APP_DEBUG,"Inside if block");

            File [] files = dir.listFiles();
            for(File file : files)
            {
                Log.d(APP_DEBUG,"File name " + file.getName());
            }
        }
    }

}
