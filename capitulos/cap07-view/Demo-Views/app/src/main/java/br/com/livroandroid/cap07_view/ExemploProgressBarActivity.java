package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ExemploProgressBarActivity extends Activity {
    private static final String TAG = "livro";
    private ProgressBar mProgress;
    private boolean alive = true;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_exemplo_progressbar);
        // Barra de Progresso
        mProgress = (ProgressBar) findViewById(R.id.barraProgresso);
        Button b = (Button) findViewById(R.id.btOK);
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            if(!alive) {
                                Log.d(TAG, "Fim Progress");
                                break;
                            }
                            final int progress = i;
                            // Atualiza a barra de progresso
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Log.d(TAG, ">> Progress: " + progress);
                                    mProgress.setProgress(progress);
                                }
                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i(TAG, "Fim.");
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alive = false;
    }
}

