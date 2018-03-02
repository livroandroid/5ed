package br.com.livroandroid.cap07_view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ExemploProgressDialogActivity extends Activity {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_exemplo_progress_dialog);
        // Abre a janela com a barra de progresso
        dialog = ProgressDialog.show(this, "Exemplo", "Buscando imagem, por aguarde...", false, true);
        downloadImagem(URL);
    }

    // Faz o download da imagem em uma nova Thread
    private void downloadImagem(final String urlImg) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // Faz o download da imagem
                    URL url = new URL(urlImg);
                    InputStream in = url.openStream();
                    // Converte a InputStream do Java para Bitmap
                    final Bitmap imagem = BitmapFactory.decodeStream(in);
                    in.close();

                    // Atualiza a tela
                    atualizaImagem(imagem);

                } catch (IOException e) {
                    // Uma aplica��o real deveria tratar este erro
                    Log.e("Erro ao fazer o download: ", e.getMessage(), e);
                }
            }
        }.start();
    }

    private void atualizaImagem(final Bitmap imagem) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Fecha a janela de progresso
                dialog.dismiss();
                ImageView imgView = (ImageView) findViewById(R.id.img);
                imgView.setImageBitmap(imagem);
            }
        });
    }
}
