package br.com.livroandroid.wearnotification;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class HelloActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
