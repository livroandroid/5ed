package br.com.livroandroid.helloservice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.util.Log;

import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by Ricardo Lecheta on 15/03/2015.
 */
public class HelloIntentService extends IntentService {
    public HelloIntentService() {
        super("NomeDaThreadAqui");
    }
    private static final int MAX = 10;
    private static final String TAG = "livro";
    private boolean running;
    @Override
    protected void onHandleIntent(Intent intent) {
        running = true;
        // Este método executa em uma thread
        // Quando ele terminar, o método stopSelf() será chamado automaticamente
        int count = 0;
        while (running && count < MAX) {
            fazAlgumaCoisa();
            Log.d(TAG, "ExemploServico executando... " + count);
            count++;
        }
        NotificationUtil.create(this,1,new Intent(this,MainActivity.class),R.mipmap.ic_launcher,"Fim","Olá");
        Log.d(TAG, "ExemploServico fim.");
    }
    private void fazAlgumaCoisa() {
        try {
            // Simula algum processamento
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Ao encerrar o serviço, altera o flag para o loop parar
        running = false;
        Log.d(TAG, "ExemploServico.onDestroy()");
    }
}
