package br.com.livroandroid.handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

/**
 * Exemplo de download de imagem com Threads
 *
 * Mostra como atualizar a interface na UI Thread com o método runOnUiThread(runnable)
 *
 * Utiliza um handler para atualizar a imagem a cada 10 segundos.
 */
public class ReDownloadImagemActivity extends AppCompatActivity {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressBar progress;
    private Handler handler = new Handler();
    private ImageView imgView;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_download_imagem);
        imgView = (ImageView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);
        downloadImagem();
    }
    // Faz o download da imagem em uma nova Thread
    private void downloadImagem() {
        Toast.makeText(this,"Download!",Toast.LENGTH_SHORT).show();
        // Zera a imagem para dar o efeito ao baixar novamente
        imgView.setImageBitmap(null);
        // Mostra o progress
        progress.setVisibility(View.VISIBLE);
        // Dispara a thread
        new Thread() {
            @Override
            public void run() {
                try {
                    // Faz o download da imagem
                    final Bitmap bitmap = Download.downloadBitmap(URL);
                    // Atualiza a tela
                    atualizaImagem(bitmap);
                    // Agenda o download novamente (daqui a 10 seg)
                    handler.postDelayed(getRunnableDownload(),2000);
                } catch (IOException e) {
                    // Uma aplicação real deveria tratar este erro
                    Log.e("Erro ao fazer o download: ", e.getMessage(), e);
                }
            }
        }.start();
    }

    private Runnable getRunnableDownload() {
        return new Runnable() {
            @Override
            public void run() {
                downloadImagem();
            }
        };
    }

    // Vai dar Erro neste método pois somente a UI Thread pode atualizar a view
    private void atualizaImagem(final Bitmap imagem) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               // Esconde o progress
               progress.setVisibility(View.INVISIBLE);
               // Atualiza a imagem
               imgView.setImageBitmap(imagem);
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancela o runnable ao sair da activity
        handler.removeCallbacksAndMessages(null);
    }
}

