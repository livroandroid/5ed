package br.com.livroandroid.asynctask.exemplo1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.livroandroid.asynctask.Download;
import br.com.livroandroid.asynctask.R;

/**
 * Exemplo de download de imagem com AsyncTask
 *
 * O ProgressDailog é fechado no onDestroy(), mas se vc girar várias vezes a tela a task
 * vai executar várias vezes.
 *
 */
public class DownloadImagemAsyncTaskActivity extends ActionBarActivity {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressDialog progress;
    private ImageView imgView;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_download_imagem);
        imgView = (ImageView) findViewById(R.id.img);

        TextView t = (TextView) findViewById(R.id.text);
        t.setText(R.string.texto_exemplo1);

        if(icicle != null) {
            // Recupera o estado
            bitmap = icicle.getParcelable("img");
        }

        if(bitmap == null) {
            // Faz o download
            downloadImagem(URL);
        } else {
            // Atualiza a imagem se recuperou o estado
            imgView.setImageBitmap(bitmap);
        }
    }
    // Faz o download da imagem em uma nova Thread
    private void downloadImagem(final String urlImg) {

        // Cria uma AsyncTask
        DownloadTask task = new DownloadTask();
        // Executa a task/thread
        task.execute(URL);
    }

    private class DownloadTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress = ProgressDialog.show(getContext(),"Aguarde","Fazendo o download... Gire a tela durante o download para fazer BOOM!");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // Faz o download da imagem
            try {
                bitmap = Download.downloadBitmap(URL);
            } catch (Exception e) {
                Log.e("livroandroid",e.getMessage(), e);
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {

                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
                // Esconde o progress
                closeProgress();

                // Atualiza a imagem
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salva o estado da tela
        outState.putParcelable("img",bitmap);
    }



    private void closeProgress() {
        if(progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }

    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Descomente para o exemplo não travar durante a rotação
        //closeProgress();
    }
}
