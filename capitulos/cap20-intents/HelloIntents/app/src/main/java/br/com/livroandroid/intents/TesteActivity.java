package br.com.livroandroid.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;


public class TesteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        TextView text = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();

        if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = intent.getData();

            text.setText("Intent: " + uri.toString());

            Log.d("livroandroid", "Scheme: " + uri.getScheme());
            Log.d("livroandroid", "Host: " + uri.getHost());
            Log.d("livroandroid", "Path: " + uri.getPath());
        }
    }

}
