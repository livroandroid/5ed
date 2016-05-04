package br.com.livroandroid.helloreceiver;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btEnviar).setOnClickListener(this);
        readMsg(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        readMsg(intent);
    }

    private void readMsg(Intent intent) {
        String msg = intent.getStringExtra("msg");
        if(msg != null) {
            Toast.makeText(this,"Você digitou: " + msg,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Extras: " + intent.getExtras(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        EditText text = (EditText) findViewById(R.id.text);
        String msg = text.getText().toString();
        Intent intent = new Intent(new Intent("BINGO"));
        intent.putExtra("msg",msg);
        sendBroadcast(intent);
        Toast.makeText(this,"Intent enviada!",Toast.LENGTH_SHORT).show();
    }
}
