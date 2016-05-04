package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.livroandroid.carros.R;

public class WriteSdCardActivity extends AppCompatActivity {

    private static final String TAG = "sdcard_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_sd_card);

        //
        File sdCardDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File f = new File(sdCardDir, "1.txt");
        writeToFile(f, "Test 1");

        sdCardDir = getExternalFilesDir(null);
        f = new File(sdCardDir, "2.txt");
        writeToFile(f, "Test 2");

        // OK
        sdCardDir = Environment.getExternalStorageDirectory();
        f = new File(sdCardDir, "3.txt");
        writeToFile(f, "Test 3");

        //
        sdCardDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        f = new File(sdCardDir, "4.txt");
        writeToFile(f, "Test 4");
    }

    private void writeToFile(File f, String s) {
        try {
            Log.d(TAG, String.format("write file [%s]", f.toString()));
            byte[] bytes = s.getBytes();
            FileOutputStream out = new FileOutputStream(f);
            out.write(bytes);
            out.flush();
            out.close();
            Log.d(TAG, String.format("write file [%s] OK", f.getName()));
        } catch (IOException e) {
            Log.d(TAG, String.format("write file [%s] ERROR", f.getName()));
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
