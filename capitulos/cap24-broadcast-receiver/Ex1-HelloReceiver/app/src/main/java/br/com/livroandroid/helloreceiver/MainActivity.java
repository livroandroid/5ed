package br.com.livroandroid.helloreceiver;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btEnviar).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        sendBroadcast(new Intent("BINGO"));
        Toast.makeText(this,"Intent enviada!",Toast.LENGTH_SHORT).show();
    }
}
