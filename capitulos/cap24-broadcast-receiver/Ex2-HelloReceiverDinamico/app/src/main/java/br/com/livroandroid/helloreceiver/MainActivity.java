package br.com.livroandroid.helloreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private BroadcastReceiver helloReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("livroandroid", "HelloReceiver Dinamico!!!");
            TextView text = (TextView) findViewById(R.id.text);
            text.setText("Mensagem recebida pelo HelloReceiver Dinamico!!!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btEnviar).setOnClickListener(this);

        // Registra o receiver
        registerReceiver(helloReceiver,new IntentFilter("BINGO"));
    }
    @Override
    public void onClick(View v) {
        sendBroadcast(new Intent("BINGO"));
        Toast.makeText(this,"Intent enviada!",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancela o receiver
        unregisterReceiver(helloReceiver);
    }
}
