package br.com.livroandroid.hellonotification;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MensagemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String msg = getIntent().getStringExtra("msg");

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(msg);
    }
}
