package br.com.livroandroid.wearnotification;

import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import livroandroid.lib.utils.NotificationUtil;


public class ReplyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(getMessageText(getIntent()));

        NotificationUtil.cancellAll(this);
    }

    // Lê a resposta da voice input
    private String getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            // Mesma chave utilizada para criar a intent voice input
            CharSequence c = remoteInput.getCharSequence("remote.input.key");
            return c != null ? c.toString() : null;
        }
        return null;
    }
}
