package br.com.livroandroid.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Mostra como fazer um contador com o handler de forma que o codigo fique se atualizando
 */
public class ContadorActivity extends AppCompatActivity implements Runnable {
    private static final String CATEGORIA = "livro";
    private int count;
    private TextView text;
    private Handler handler;
    private boolean running;
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_thread_sleep);
        text = (TextView) findViewById(R.id.text);
        running = true;
        handler = new Handler();
        handler.post(this);
    }
    @Override
    public void run() {
        if (running) {
            count++;
            Log.i(CATEGORIA, "Handler.run() count: " + count);
            text.setText("Contador: " + count);
            // Repetir depois de 1 segundo
            handler.postDelayed(this, 1000);
        } else {
            Log.i(CATEGORIA, "Handler fim.");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Encerrar as mensagens do handler
        Log.i(CATEGORIA,"onDestroy()");
        running = false;
    }
}

